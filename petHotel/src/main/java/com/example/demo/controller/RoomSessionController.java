package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.RoomSession;
import com.example.demo.model.Rooms;
import com.example.demo.model.Users;
import com.example.demo.service.RoomSessionService;
import com.example.demo.service.RoomsService;
import com.example.demo.service.UsersService;

@RestController
public class RoomSessionController {
	
	@Autowired
	private RoomsService roomsService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RoomSessionService roomSessionService;

	@GetMapping({"/findRoomSession"})
	public ModelAndView findRoomSession() {

		ModelAndView model = new ModelAndView("ianTest/findRoomSession");
		// 搜尋全部房型
		Iterable<Rooms> roomses = roomsService.getRoomses();
		model.addObject("roomses", roomses);
		// 房型選單內容
		Rooms rs = roomses.iterator().next();
		model.addObject("rooms", rs);
		
		// 搜尋全部使用者
		Iterable<Users> userses = usersService.getUserses();
		model.addObject("userses", userses);
		// 使用者選單內容
		Users us = userses.iterator().next();
		model.addObject("users", us);
		
		// 搜尋全部 RoomSession
		Iterable<RoomSession> roomSession = roomSessionService.getRoomSessions();
		model.addObject("roomSession", roomSession);
		
		return model;
	}
	
	@PostMapping({"/findRoomSessionByRooms"})
	public ModelAndView findRoomSessionByRooms(
		@RequestParam(value = "id", required = false, defaultValue = "1") Integer id) {
		
		ModelAndView model = new ModelAndView("ianTest/findRoomSession");
		
		// 固定顯示使用者 #1
		Iterable<Users> userses = usersService.getUserses();
		model.addObject("userses", userses);
		Users us = usersService.getUsersById(1);
		model.addObject("users", us);
		
		Iterable<Rooms> roomses = roomsService.getRoomses();
		model.addObject("roomses", roomses);
		
		Rooms rs = roomsService.getRoomsById(id);
		
		model.addObject("rooms", rs);
		model.addObject("roomSession", rs.getRoomSessions());
		
		return model;
	}
	
	@PostMapping({"/findRoomSessionByUsers"})
	public ModelAndView findRoomSessionByUsers(
		@RequestParam(value = "id", required = false, defaultValue = "1") Integer id) {
		
		ModelAndView model = new ModelAndView("ianTest/findRoomSession");
		// 固定顯示房型 #1
		Iterable<Rooms> roomses = roomsService.getRoomses();
		model.addObject("roomses", roomses);
		Rooms rs = roomsService.getRoomsById(1);
		model.addObject("rooms", rs);
		
		// 依 id 顯示使用者
		Iterable<Users> userses = usersService.getUserses();
		model.addObject("userses", userses);
		Users us = usersService.getUsersById(id);
		
		model.addObject("users", us);
		model.addObject("roomSession", us.getRoomSessions());
		
		return model;
	}
	

}
