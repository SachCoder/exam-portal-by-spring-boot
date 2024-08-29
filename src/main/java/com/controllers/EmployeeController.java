package com.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.CustomUserDetails;
import com.service.EmployeeService;
import com.service.UserService;

import dto.EmployeeDto;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/employee")
public class EmployeeController {

	private final EmployeeService employeeService;
 
 

	public EmployeeController(EmployeeService employeeService ) {
		this.employeeService = employeeService;
	 
	}

	@PostMapping("/add")
	public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeDto employee,BindingResult result) {
//		System.out.println("hi");
		if(result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for(FieldError error:result.getFieldErrors()){
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.badRequest().body(errors);
			
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails obj =(CustomUserDetails) auth.getPrincipal();
		  String id = obj.getId();
		  
		EmployeeDto savedEmployee = employeeService.saveEmployee(employee,id);
		if (savedEmployee != null)
			return ResponseEntity.ok(savedEmployee);
		else
			return ResponseEntity.badRequest().body("Email or Phone already registered");
	}

	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<?> updateEmployee(@Valid @RequestBody EmployeeDto employee, @PathVariable int id) {
		if (employeeService.getEmployeeById(id)!=null) {
			employee.setEmp_id(id);
			EmployeeDto updatedEmployee = employeeService.updateEmployee(employee);
			if (updatedEmployee == null) {
				return ResponseEntity.badRequest().body("Email or Phone already registered");
			} else
				return ResponseEntity.ok(updatedEmployee);
		} else
			return ResponseEntity.badRequest().body("Invalid id");

	}

	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable int id) {
		if (employeeService.getEmployeeById(id)!=null) {
			employeeService.deleteEmployee(id);
			return ResponseEntity.ok().build();
		} else
			return ResponseEntity.badRequest().body("Invalid id");
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable int id) {
		System.out.println("hi");
		EmployeeDto employee = employeeService.getEmployeeById(id);
		return employee != null ? ResponseEntity.ok(employee) : ResponseEntity.badRequest().body("Invalid id");
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllEmployees() {
		List<EmployeeDto> employees = employeeService.getAllEmployees();
		return ResponseEntity.ok(employees);
	}

}
