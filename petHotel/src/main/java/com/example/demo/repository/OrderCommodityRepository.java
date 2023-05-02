package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.OrderCommodity;


public interface OrderCommodityRepository extends JpaRepository<OrderCommodity, Integer> {
	@Query(value = "select * from order_commodity where created_at >= ?1 and created_at<=?2", nativeQuery = true)
	List<OrderCommodity> findByCreatedAtBetween(String start_at,String end_at);

}
