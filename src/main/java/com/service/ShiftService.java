package com.service;

import com.entities.Shift;

import dto.ShiftDto;

import java.util.List;
import java.util.Optional;

public interface ShiftService {
    ShiftDto saveShift(ShiftDto shift);
    ShiftDto updateShift(ShiftDto shift);
    void deleteShift(String shiftId);
    ShiftDto getShiftById(String shiftId);
    List<ShiftDto> getAllShifts();
}
