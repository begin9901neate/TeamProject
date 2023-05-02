package com.example.demo.service;

import java.util.List;

import com.example.demo.model.OrderRooms;


public interface OrderRoomsService {
	
    // Create
    void saveOrderRooms(OrderRooms orderRooms);

    
    // Read: All
    List<OrderRooms> getOrderRoomses();
    List <OrderRooms> getAllOrderRooms();			//取得所有訂房資料
	List <OrderRooms> getByCreated_atBetween(String created_at,String created_at2);	//取得近期訂房資料

    
    
    // Read: by Id
    OrderRooms getOrderRoomsById(Integer id);

    
    // Update
    void updateOrderRooms(OrderRooms orderRooms);
    
    // Delete
    void deleteOrderRoomsById(Integer id);


}
