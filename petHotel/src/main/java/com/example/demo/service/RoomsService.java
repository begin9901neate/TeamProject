package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Rooms;


public interface RoomsService {
		
	
    // Create & Update
    void saveRooms(Rooms rooms);
    void saveRoom(Rooms room);
    
    // Read: All
    List<Rooms> getRoomses();
    List <Rooms> getAllRoom();
    
    
    // Read: by Id
    Rooms getRoomsById(Integer id);
    Rooms getRoomById(Integer id);
    List<Rooms> findByBranchId(Integer id);
    
    // Delete
    void deleteRoomsById(Integer id);
    void deleteRoomById(Integer id);
    

}
