package com.service;


import com.entities.Shift;
import com.repos.ShiftRepo;

import dto.ShiftDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShiftServiceImpl implements ShiftService {

    
    private ShiftRepo shiftRepository;
	private ModelMapper modelMapper;

    
    public ShiftServiceImpl(ShiftRepo shiftRepository, ModelMapper modelMapper) {
		super();
		this.shiftRepository = shiftRepository;
		this.modelMapper = modelMapper;
	}


    @Override
    public ShiftDto saveShift(ShiftDto shift) {
         Shift save = shiftRepository.save(modelMapper.map(shift, Shift.class));
         return modelMapper.map(save, ShiftDto.class);
    }

    @Override
    public ShiftDto updateShift(ShiftDto shift) {
    	Shift save = shiftRepository.save(modelMapper.map(shift, Shift.class));
        return modelMapper.map(save, ShiftDto.class);
    }

    @Override
    public void deleteShift(String shiftId) {
        shiftRepository.deleteById(shiftId);
    }

    @Override
    public ShiftDto getShiftById(String shiftId) {
        return modelMapper.map(shiftRepository.findById(shiftId), ShiftDto.class);
    }

    @Override
    public List<ShiftDto> getAllShifts() {
        return shiftRepository.findAll().stream().map(s->modelMapper.map(s, ShiftDto.class)).toList();
    }
}
