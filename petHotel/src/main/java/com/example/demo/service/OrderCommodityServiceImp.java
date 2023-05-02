package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.OrderCommodity;
import com.example.demo.model.OrderRooms;
import com.example.demo.repository.OrderCommodityRepository;

@Service
public class OrderCommodityServiceImp  implements OrderCommodityService {

	@Autowired // 由系統產生物件OrderRooms
	private OrderCommodityRepository OrderCommoditysRepository;

	@Override
	public List<OrderCommodity> getAllOrderCommoditys() {
		return OrderCommoditysRepository.findAll(); // .findAll()為系統內建method，所以不用寫內容
	}

	@Override
	public List<OrderCommodity> getByCreated_atBetween(String start_at,String end_at) {
		List<OrderCommodity> OrderCommodity = OrderCommoditysRepository.findByCreatedAtBetween(start_at,end_at);
		
		return OrderCommodity;
	}

}
