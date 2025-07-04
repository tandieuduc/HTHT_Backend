package com.vnpt.hethonghotro.controller;


import com.vnpt.hethonghotro.dto.RoomDTO.RoomDTO;
import com.vnpt.hethonghotro.dto.RoomDTO.RoomSaveDTO;
import com.vnpt.hethonghotro.dto.RoomDTO.RoomUpdateDTO;
import com.vnpt.hethonghotro.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping(path = "/save-room")
    public String saveRoom(@RequestBody RoomSaveDTO roomSaveDTO)
    {
        String name = roomService.addRoom(roomSaveDTO);
        return name;
    }

    @GetMapping(path = "/get-all-room")
    public List<RoomDTO> getAllRoom()
    {
        List<RoomDTO>allRooms = roomService.getAllRoom();
        return allRooms;
    }

    @PutMapping(path = "/update-room")
    public String updateRoom(@RequestBody RoomUpdateDTO roomUpdateDTO)
    {
        String name = roomService.updateRoom(roomUpdateDTO);
        return name;
    }

    @DeleteMapping(path = "/delete-room/{id}")
    public String deleteRoom(@PathVariable(value = "id") String id)
    {
        boolean deleteroom = roomService.deleteRoom(id);
        if (deleteroom) {
            return "Phòng ban đã được xóa mềm (is_deleted = true)";
        } else {
            return "Không tìm thấy phòng ban với id: " + id;
        }
    }

}
