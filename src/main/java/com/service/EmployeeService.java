package com.service;

import com.entities.Employee;

import dto.EmployeeDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employee, String username);
    EmployeeDto updateEmployee(EmployeeDto employee);
    void deleteEmployee(int id);
    EmployeeDto getEmployeeById(int id);
    List<EmployeeDto> getAllEmployees();
}
