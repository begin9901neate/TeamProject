package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.RoomSession;
import com.example.demo.repository.RoomSessionRepository;

@Service
public class RoomSessionServiceImpl implements RoomSessionService {
	
	@Autowired
    private RoomSessionRepository roomSessionRepository;
	
	// Create & Update
	@Override
	public void saveRoomSession(RoomSession roomSession) {
		this.roomSessionRepository.save(roomSession);
		
	}
	
	// Read: All
	@Override
	public List<RoomSession> getRoomSessions() {
		return roomSessionRepository.findAll();
	}
	
	@Override
	public List<RoomSession> getAllRoomSessions() {
		return roomSessionRepository.findAll(); // .findAll()為系統內建method，所以不用寫內容
	}

	@Override
	public List<RoomSession> getByReservationTimeBetween(String start_at, String end_at) {
		List<RoomSession> RoomSession = roomSessionRepository.findByReservationTimeBetween(start_at,end_at);
		return RoomSession;
	}
	
	// Read: by Id
	@Override
	public RoomSession getRoomSessionById(Integer id) {
		
		Optional <RoomSession> optional = roomSessionRepository.findById(id);
		
		RoomSession roomSession = null;
		
        if (optional.isPresent()) {
        	roomSession = optional.get();
        } else {
            throw new RuntimeException("RoomSession not found for id: " + id);
        }
        return roomSession;
        
	}
	
	// Delete
	@Override
	public void deleteRoomSessionById(Integer id) {
		this.roomSessionRepository.deleteById(id);
		
	}

	
	
}
