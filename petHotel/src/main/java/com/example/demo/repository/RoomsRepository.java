package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Rooms;

public interface RoomsRepository extends JpaRepository<Rooms, Integer> {
	
    @Query(value = "select * from rooms where branch_id = ?1", nativeQuery = true)
    List<Rooms> findByBranchId(Integer id);

}
