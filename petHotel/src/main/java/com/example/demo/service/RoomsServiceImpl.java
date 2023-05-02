package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Rooms;
import com.example.demo.repository.RoomsRepository;


@Service
public class RoomsServiceImpl implements RoomsService {
	
	@Autowired
    private RoomsRepository roomsRepository;
	
	// Create & Update
	@Override
	public void saveRooms(Rooms rooms) {
		this.roomsRepository.save(rooms);
		
	}
	
	@Override
	public void saveRoom(Rooms room) {
		this.roomsRepository.save(room);
		
	}
	
	// Read: All
	@Override
	public List<Rooms> getRoomses() {
		return roomsRepository.findAll();
	}
	
	@Override
	public List<Rooms> getAllRoom() {
		return roomsRepository.findAll();
	}
	
	// Read: by Id
	@Override
	public Rooms getRoomsById(Integer id) {
		
		Optional <Rooms> optional = roomsRepository.findById(id);
		
		Rooms rooms = null;
		
        if (optional.isPresent()) {
        	rooms = optional.get();
        } else {
            throw new RuntimeException("Rooms not found for id: " + id);
        }
        return rooms;
        
	}
	
	@Override
	public Rooms getRoomById(Integer id) {
        Optional<Rooms> optional = roomsRepository.findById(id);
        Rooms room = null;
        if (optional.isPresent()) {
            room = optional.get();
        } else {
            throw new RuntimeException(" Room not found for id :: " + id);
        }
        return room;
	}

	@Override
	public List<Rooms> findByBranchId(Integer id) {
		return roomsRepository.findByBranchId(id);
	}
	
	// Delete
	@Override
	public void deleteRoomsById(Integer id) {
		this.roomsRepository.deleteById(id);
	}
	
	@Override
	public void deleteRoomById(Integer id) {
		this.roomsRepository.deleteById(id);
		
	}

	
	
}
