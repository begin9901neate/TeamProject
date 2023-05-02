package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.RoomSession;
import com.example.demo.model.Rooms;

public interface RoomSessionRepository extends JpaRepository<RoomSession, Integer> {
	
	@Query(value = "select * from room_session where reservation_time >= ?1 and reservation_time<=?2", nativeQuery = true)
	List<RoomSession> findByReservationTimeBetween(String start_at,String end_at);

}
