package com.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entities.Shift;

@Repository
public interface ShiftRepo extends JpaRepository<Shift, String> {

}