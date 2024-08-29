package com.service;

import com.entities.Employee;
import com.entities.User;
import com.repos.EmployeeRepo;
import com.repos.UserRepo;

import dto.EmployeeDto;
import dto.UserCreateDto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepo employeeRepo;
	private final UserRepo userRepo;
    
    private final ModelMapper modelMapper;


    public EmployeeServiceImpl(EmployeeRepo employeeRepo,ModelMapper modelMapper,UserRepo userRepo) {
        this.employeeRepo = employeeRepo;
		this.userRepo = userRepo;
        this.modelMapper=modelMapper;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employee,String username) {
//    	System.out.println("hi");
    	if(employeeRepo.findById(employee.getEmp_id()).isEmpty()) {
//    		System.out.println("an");
    		
//    		Optional<Employee> emp = employeeRepo.findById(employee.getEmp_id());
    		
    		 
    			Optional<User>  user = userRepo.findById(username);
    			if(user.isPresent()) {
    				User u1 = user.get();
//    				System.out.println(user);
    				UserCreateDto userDto =modelMapper.map(u1, UserCreateDto.class) ;
//    				System.out.println(userDto);
        			employee.setUser(userDto);
        		
    			}
//    			
 
    		Employee save = employeeRepo.save(modelMapper.map(employee, Employee.class));
    		return modelMapper.map(save, EmployeeDto.class) ;
    	}
    	else
    		return null;
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employee) {
    	 // Check if the provided email and phone are unique
//        boolean isEmailOrPhoneInUse = employeeRepo.findByPhoneOrEmail(employee.getPhone(), employee.getEmail()).stream()
//                .anyMatch(e -> !e.getEmp_id().equals(employee.getEmp_id()));
//
//        if (isEmailOrPhoneInUse) {
//            // Email or phone is in use by another employee
//            return null; // or throw an appropriate exception, e.g., throw new ConflictException("Email or phone already in use");
//        }

        // Map DTO to entity and update
        Employee updatedEmployee = modelMapper.map(employee, Employee.class);
        updatedEmployee.setEmp_id(employee.getEmp_id()); // Ensure the ID is set
        Employee savedEmployee = employeeRepo.save(updatedEmployee);

        // Return the updated employee as DTO
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public void deleteEmployee( int id) {
        employeeRepo.deleteById(id);
    }

    @Override
    public EmployeeDto getEmployeeById(int id) {
         Optional<Employee> optional = employeeRepo.findById(id);
         if(optional.isPresent()) {
        	 return modelMapper.map(optional.get(), EmployeeDto.class) ;
         }
		return null;
         
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepo.findAll().stream().map(e->modelMapper.map(e, EmployeeDto.class)).toList();
    }
}
