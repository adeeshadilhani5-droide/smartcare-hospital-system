package com.smartcare.system.controller;

import com.smartcare.system.entity.Room;
import com.smartcare.system.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    // Get Available Rooms (GET) - No @RequestBody required
    @GetMapping("/available")
    public List<Room> getAvailableRooms() {
        return roomRepository.findByIsAvailableTrue();
    }
}