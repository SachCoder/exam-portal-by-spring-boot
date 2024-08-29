package com.service;

import dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto updateDepartment(DepartmentDto departmentDto);

    void deleteDepartment(String departmentId);

    DepartmentDto getDepartmentById(String departmentId);

    List<DepartmentDto> getAllDepartments();
}
