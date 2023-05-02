package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.OrderRooms;
import com.example.demo.model.Rooms;
import com.example.demo.model.Users;
import com.example.demo.service.OrderRoomsService;
import com.example.demo.service.RoomsService;
import com.example.demo.service.UsersService;

@RestController
public class ReservationController {
	
	@Autowired
	private RoomsService roomsService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private OrderRoomsService orderRoomsService;
/***************************************************************************************/
	// OrderRooms 頁面呈現
	@GetMapping({"/reservation"})
	public ModelAndView reservation() {

		ModelAndView model = new ModelAndView("forestage/catReservation");
		
		// 搜尋全部房型
		List<Rooms> roomses = roomsService.getRoomses();
		model.addObject("roomses", roomses);
		
		// 使用者
		Users users = usersService.getUsersById(3);
		model.addObject("users", users);
		
		return model;
	}
/***************************************************************************************/
	// Create OrderRoom
	@PostMapping("/createReservation")
	public ModelAndView createReservation(@ModelAttribute OrderRooms orderRooms, 
			@RequestParam("roomNameInput") String roomName, 
			@RequestParam("checkInTimeInput") String checkInTimeInput, 
			@RequestParam("reservationTimeInput") String reservationTimeInput) {
		ModelAndView model = new ModelAndView("forestage/catResult");
		try {
			orderRoomsService.saveOrderRooms(orderRooms);
			model.addObject(orderRooms);
			model.getModel().put("result", "Succeed");
			model.getModel().put("checkInTime", checkInTimeInput);
			model.getModel().put("reservationTime", reservationTimeInput);
			model.getModel().put("room", roomName);
			model.getModel().put("payment", orderRooms.getPaymentMethod());
			model.getModel().put("complete", orderRooms.getComplete());
		} catch(Exception e) {
			e.getMessage();
			model.getModel().put("result", "Failed");
		}
		return model;
	}
	

}
