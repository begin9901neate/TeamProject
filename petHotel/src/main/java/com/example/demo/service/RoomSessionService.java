package com.example.demo.service;

import java.util.List;

import com.example.demo.model.RoomSession;


public interface RoomSessionService {
	
    // Create & Update
    void saveRoomSession(RoomSession roomSession);
    
    // Read: All
    List<RoomSession> getRoomSessions();
    List <RoomSession> getAllRoomSessions();			//取得所有入住資料
   	List <RoomSession> getByReservationTimeBetween(String start_at,String end_at);		//取得近期新增入住資訊
       
    
    // Read: by Id
    RoomSession getRoomSessionById(Integer id);
    
    // Delete
    void deleteRoomSessionById(Integer id);

}
