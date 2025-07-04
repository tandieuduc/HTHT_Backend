package com.vnpt.hethonghotro.service;

import com.vnpt.hethonghotro.dto.RoomDTO.RoomDTO;
import com.vnpt.hethonghotro.dto.RoomDTO.RoomSaveDTO;
import com.vnpt.hethonghotro.dto.RoomDTO.RoomUpdateDTO;
import com.vnpt.hethonghotro.entity.PhongBan;
import com.vnpt.hethonghotro.repository.PhongBanRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceIMPL implements RoomService{

    @Autowired
    private PhongBanRepositoy phongBanRepositoy;

    @Override
    public String addRoom(RoomSaveDTO roomSaveDTO) {

        PhongBan phongBan = new PhongBan(
            roomSaveDTO.getId(),
            roomSaveDTO.getName(),
            roomSaveDTO.getPhone(),
            true
        );
        if (phongBanRepositoy.existsById(roomSaveDTO.getId())) {
            throw new RuntimeException("ID phòng ban đã tồn tại!");
        }
        System.out.println("🟢 ID nhận vào: " + roomSaveDTO.getId());
        phongBanRepositoy.save(phongBan);
        System.out.println("Thêm mới phòng ban thành công");
        return phongBan.getName();
    }

    @Override
    public List<RoomDTO> getAllRoom() {
        List<PhongBan> getRooms = phongBanRepositoy.findAll();
        List<RoomDTO> roomDTOList = new ArrayList<>();
        for(PhongBan a:getRooms)
        {
              RoomDTO roomDTO = new RoomDTO(
                  a.getId(),
                  a.getName(),
                  a.getPhone(),
                  a.isState()
              );
              roomDTOList.add(roomDTO);
        }
        return roomDTOList;
    }

    @Override
    public String updateRoom(RoomUpdateDTO roomUpdateDTO)
    {
        if (phongBanRepositoy.existsById(roomUpdateDTO.getId())) {
            PhongBan room = phongBanRepositoy.getById(roomUpdateDTO.getId());
            // Khoong update mã số phòng ban
            room.setName(roomUpdateDTO.getName());
            room.setPhone(roomUpdateDTO.getPhone());
            room.setState(roomUpdateDTO.isState());
            phongBanRepositoy.save(room);
            System.out.println("Cập nhật phòng ban thành công");
            return room.getName();
        }
        else {
            System.out.println("Mã số phòng ban không tìm thấy");
        }
        return null;
    }

    @Override
    public boolean deleteRoom(String id) {

        if (phongBanRepositoy.existsById(id))
        {
            PhongBan room = phongBanRepositoy.getById(id);
            room.setState(false);
            phongBanRepositoy.save(room);
//            System.out.println("Đã xóa mềm thông tin phòng ban");
            return true;
        }
        else {
//            System.out.println("Phòng ban không tìm thấy");
            return false;
        }
    }

    @Override
    public boolean deleteRoomForever(String id) {
        if (phongBanRepositoy.existsById(id)) {
            PhongBan room = phongBanRepositoy.getById(id);
            phongBanRepositoy.deleteById(id);
            System.out.println("Đã xóa phòng ban trong CSDL");
            return true;
        }
        else {
            System.out.println("Không tìm thấy phòng ban");
            return false;
        }
    }
}
