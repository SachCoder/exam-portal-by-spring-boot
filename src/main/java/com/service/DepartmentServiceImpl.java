package com.service;

import com.entities.Department;
import com.repos.DepartmentRepo;
import dto.DepartmentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepo departmentRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepo departmentRepo, ModelMapper modelMapper) {
        this.departmentRepo = departmentRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto, Department.class);
        Department savedDepartment = departmentRepo.save(department);
        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        if (!departmentRepo.existsById(departmentDto.getDepart_Id())) {
            return null; // Or throw an exception
        }
        Department department = modelMapper.map(departmentDto, Department.class);
        Department updatedDepartment = departmentRepo.save(department);
        return modelMapper.map(updatedDepartment, DepartmentDto.class);
    }

    @Override
    public void deleteDepartment(String departmentId) {
        departmentRepo.deleteById(departmentId);
    }

    @Override
    public DepartmentDto getDepartmentById(String departmentId) {
        Optional<Department> department = departmentRepo.findById(departmentId);
        return department.map(d -> modelMapper.map(d, DepartmentDto.class)).orElse(null);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentRepo.findAll().stream()
                .map(department -> modelMapper.map(department, DepartmentDto.class))
                .collect(Collectors.toList());
    }
}
