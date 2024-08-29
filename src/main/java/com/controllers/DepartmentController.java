package com.controllers;

import com.service.DepartmentService;
import dto.DepartmentDto;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> createDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
        return ResponseEntity.ok(savedDepartment);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDepartment(@RequestBody DepartmentDto departmentDto, @PathVariable String id) {
        departmentDto.setDepart_Id(id);
        DepartmentDto updatedDepartment = departmentService.updateDepartment(departmentDto);
        return updatedDepartment != null ? ResponseEntity.ok(updatedDepartment) : ResponseEntity.badRequest().body("Invalid id or department not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable String id) {
        if (departmentService.getDepartmentById(id) != null) {
            departmentService.deleteDepartment(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Invalid id");
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable String id) {
        DepartmentDto department = departmentService.getDepartmentById(id);
        return department != null ? ResponseEntity.ok(department) : ResponseEntity.badRequest().body("Invalid id");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllDepartments() {
        List<DepartmentDto> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }
}
