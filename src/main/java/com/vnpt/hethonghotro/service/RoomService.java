package com.vnpt.hethonghotro.service;

import com.vnpt.hethonghotro.dto.RoomDTO.RoomDTO;
import com.vnpt.hethonghotro.dto.RoomDTO.RoomSaveDTO;
import com.vnpt.hethonghotro.dto.RoomDTO.RoomUpdateDTO;

import java.util.List;

public interface RoomService {
    String addRoom(RoomSaveDTO roomSaveDTO);

    List<RoomDTO> getAllRoom();

    String updateRoom(RoomUpdateDTO roomUpdateDTO);

    boolean deleteRoom(String id);

    boolean deleteRoomForever(String id);
}
