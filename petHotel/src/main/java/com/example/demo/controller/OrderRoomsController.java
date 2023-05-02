package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.OrderRooms;
import com.example.demo.model.Rooms;
import com.example.demo.model.Users;
import com.example.demo.service.OrderRoomsService;
import com.example.demo.service.RoomsService;
import com.example.demo.service.UsersService;

@RestController
public class OrderRoomsController {
	
	@Autowired
	private RoomsService roomsService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private OrderRoomsService orderRoomsService;
/***************************************************************************************/
	// AdminOrderRooms 頁面呈現
	@GetMapping({"/adminOrderRooms"})
	public ModelAndView adminOrderRooms() {

		ModelAndView model = new ModelAndView("admin/adminOrderRooms");
		// 搜尋全部房型
		Iterable<Rooms> roomses = roomsService.getRoomses();
		model.addObject("roomses", roomses);
		// 房型選單內容
		Rooms rooms = roomses.iterator().next();
		model.addObject("rooms", rooms);
		
		// 搜尋全部使用者
		Iterable<Users> userses = usersService.getUserses();
		model.addObject("userses", userses);
		// 使用者選單內容
		Users users = userses.iterator().next();
		model.addObject("users", users);
		
		// 搜尋全部 Room Order
		Iterable<OrderRooms> orderRooms = orderRoomsService.getOrderRoomses();
		model.addObject("orderRooms", orderRooms);
		
		return model;
	}
/***************************************************************************************/
	// Mapping adminOrderRoomsAdd
	@GetMapping("/adminOrderRoomsAdd")
    public ModelAndView adminOrderRoomsAddPage(ModelMap map) {
        return new ModelAndView("admin/adminOrderRoomsAdd");
    }
/***************************************************************************************/
	// Create OrderRoom
	@PostMapping("/createOrderRoom")
	public ModelAndView createOrderRoom(@ModelAttribute OrderRooms orderRooms) {
		ModelAndView model = new ModelAndView("redirect:/adminOrderRooms");
		orderRoomsService.saveOrderRooms(orderRooms);
		model.addObject(orderRooms);
		return model;
	}
/***************************************************************************************/
	// Find OrderRooms By Rooms
	@PostMapping({"/findOrderRoomsByRooms"})
	public ModelAndView findOrderRoomsByRooms(
		@RequestParam(value = "id", required = false, defaultValue = "1") Integer id) {
		
		ModelAndView model = new ModelAndView("admin/adminOrderRooms");
		
		// 選單固定顯示使用者 #1
		Iterable<Users> userses = usersService.getUserses();
		model.addObject("userses", userses);
		Users us = usersService.getUsersById(1);
		model.addObject("users", us);
		
		// 依 id 顯示房型
		Iterable<Rooms> roomses = roomsService.getRoomses();
		model.addObject("roomses", roomses);
		
		Rooms rooms = roomsService.getRoomsById(id);
		
		model.addObject("rooms", rooms);
		model.addObject("orderRooms", rooms.getOrderRoomses());
		
		return model;
	}
/***************************************************************************************/
	// Find OrderRooms By Users
	@PostMapping({"/findOrderRoomsByUsers"})
	public ModelAndView findOrderRoomsByUsers(
		@RequestParam(value = "id", required = false, defaultValue = "1") Integer id) {
		
		ModelAndView model = new ModelAndView("admin/adminOrderRooms");
		// 選單固定顯示房型 #1
		Iterable<Rooms> roomses = roomsService.getRoomses();
		model.addObject("roomses", roomses);
		Rooms rooms = roomsService.getRoomsById(1);
		model.addObject("rooms", rooms);
		
		// 依 id 顯示使用者
		Iterable<Users> userses = usersService.getUserses();
		model.addObject("userses", userses);
		Users users = usersService.getUsersById(id);
		
		model.addObject("users", users);
		model.addObject("orderRooms", users.getOrderRoomses());
		
		return model;
	}
/***************************************************************************************/
	// Update OrderRooms
	@GetMapping("/updateOrderRoom")
	public ModelAndView updateOrderRoom(@RequestParam(value = "id", required = false, defaultValue = "1") Integer id) {
		ModelAndView model = new ModelAndView("admin/adminOrderRoomsUpdate");
		
		OrderRooms orderRooms=orderRoomsService.getOrderRoomsById(id);
		model.addObject("orderRooms", orderRooms);
		
		return model;
	}
	
	@PostMapping("/updateOrderRoom")
	public ModelAndView updateOrderRoom(@ModelAttribute OrderRooms orderRooms) {
		ModelAndView model = new ModelAndView("redirect:/adminOrderRooms");
		
		orderRoomsService.updateOrderRooms(orderRooms);
		model.addObject(orderRooms);
		
		return model;
	}
/***************************************************************************************/
	// Delete OrderRooms
	@GetMapping("/deleteOrderRoom")
	public ModelAndView deleteOrderRoom(@RequestParam(value = "id", required = false, defaultValue = "1") Integer id) {
		ModelAndView model = new ModelAndView("redirect:/adminOrderRooms");
		
		orderRoomsService.deleteOrderRoomsById(id);
		return model;
	}
	
}
