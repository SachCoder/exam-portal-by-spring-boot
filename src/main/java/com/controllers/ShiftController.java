package com.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.ShiftService;

import dto.ShiftDto;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/shift")
public class ShiftController {

	private final ShiftService shiftService;

	public ShiftController(ShiftService shiftService) {
		this.shiftService = shiftService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> createShift(@Valid @RequestBody ShiftDto shiftDto) {
		ShiftDto savedShift = shiftService.saveShift(shiftDto);
		if (savedShift != null) {
			return ResponseEntity.ok(savedShift);
		} else {
			return ResponseEntity.badRequest().body("Error saving shift");
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateShift(@RequestBody ShiftDto shiftDto, @PathVariable String id) {

		if (shiftService.getShiftById(id) != null) {
			shiftDto.setShift_id(id);
			ShiftDto updatedShift = shiftService.updateShift(shiftDto);
			return ResponseEntity.ok(updatedShift);
		} else {
			return ResponseEntity.badRequest().body("invalid id");
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteShift(@PathVariable String id) {
		ShiftDto existingShift = shiftService.getShiftById(id);
		if (existingShift != null) {
			shiftService.deleteShift(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().body("Invalid id");
		}
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getShiftById(@PathVariable String id) {
		ShiftDto shift = shiftService.getShiftById(id);
		return shift != null ? ResponseEntity.ok(shift) : ResponseEntity.badRequest().body("Invalid id");
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllShifts() {
		List<ShiftDto> shifts = shiftService.getAllShifts();
		return ResponseEntity.ok(shifts);
	}
}
