package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.OrderRooms;

public interface OrderRoomsRepository extends JpaRepository<OrderRooms, Integer> {
	
	@Query(value = "select * from order_rooms where created_at >= ?1 and created_at<=?2", nativeQuery = true)
	List<OrderRooms> findByCreatedAtBetween(String start_at,String end_at);

}
