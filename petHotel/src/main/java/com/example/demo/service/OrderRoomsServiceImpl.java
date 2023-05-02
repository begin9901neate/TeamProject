package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.OrderRooms;
import com.example.demo.model.Rooms;
import com.example.demo.model.Users;
import com.example.demo.repository.OrderRoomsRepository;
import com.example.demo.repository.RoomsRepository;
import com.example.demo.repository.UsersRepository;


@Service
public class OrderRoomsServiceImpl implements OrderRoomsService {
	
	@Autowired
    private OrderRoomsRepository orderRoomsRepository;
	
	@Autowired
	private RoomsRepository roomsRepository;
	
	private UsersService usersRepository;
	
	// Create
	@Override
	public void saveOrderRooms(OrderRooms orderRooms) {
		this.orderRoomsRepository.save(orderRooms);
		
	}

	// Read: All
	@Override
	public List<OrderRooms> getOrderRoomses() {
		return orderRoomsRepository.findAll();
	}
	
	@Override
	public List<OrderRooms> getAllOrderRooms() {
		return orderRoomsRepository.findAll(); // .findAll()為系統內建method，所以不用寫內容
	}

	@Override
	public List<OrderRooms> getByCreated_atBetween(String start_at,String end_at) {
		List<OrderRooms> OrderRoom = orderRoomsRepository.findByCreatedAtBetween(start_at,end_at);
		
		return OrderRoom;
	}
	
	// Read: by Id
	@Override
	public OrderRooms getOrderRoomsById(Integer id) {
		
		Optional <OrderRooms> optional = orderRoomsRepository.findById(id);
		
		OrderRooms orderRooms = null;
		
        if (optional.isPresent()) {
        	orderRooms = optional.get();
        	
        } else {
            throw new RuntimeException("OrderRooms not found for id: " + id);
        }
        return orderRooms;
        
	}
	
	// Update
	@Override
	public void updateOrderRooms(OrderRooms orderRooms) {

		OrderRooms or = orderRoomsRepository.getReferenceById(orderRooms.getId());
		
		if (or != null) {
        	or.setUsers(orderRooms.getUsers());
    		or.setRooms(orderRooms.getRooms());
    		or.setPaymentMethod(orderRooms.getPaymentMethod());
    		or.setAmount(orderRooms.getAmount());
    		or.setComplete(orderRooms.getComplete());
    		or.setDeletedReason(orderRooms.getDeletedReason());
    		orderRoomsRepository.save(or);
        } else {
            throw new RuntimeException("OrderRooms not found for id: " + orderRooms.getId() );
        }
	}
	
	// Delete
	@Override
	public void deleteOrderRoomsById(Integer id) {
		this.orderRoomsRepository.deleteById(id);
		
	}


	



	
	
}
