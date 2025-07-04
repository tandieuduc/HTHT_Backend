package com.vnpt.hethonghotro.controller;

import com.vnpt.hethonghotro.dto.PositionDTO.PositionDTO;
import com.vnpt.hethonghotro.dto.PositionDTO.PositionSaveDTO;
import com.vnpt.hethonghotro.dto.PositionDTO.PositionUpdateDTO;
import com.vnpt.hethonghotro.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {
    @Autowired
    private PositionService positionService;

    @PostMapping(path = "/save-position")
    public String saveRoom(@RequestBody PositionSaveDTO positionSaveDTO)
    {
        String name = positionService.addPosition(positionSaveDTO);
        return name;
    }

    @GetMapping(path = "/get-all-position")
    public List<PositionDTO> getAllRoom()
    {
        List<PositionDTO>allPositions = positionService.getAllPosition();
        return allPositions;
    }

    @PutMapping(path = "/update-position")
    public String updateRoom(@RequestBody PositionUpdateDTO positionUpdateDTO)
    {
        String name = positionService.updatePosition(positionUpdateDTO);
        return name;
    }

    @DeleteMapping(path = "/delete-position/{id}")
    public String deleteRoom(@PathVariable(value = "id") String id) {
        boolean deletePosition = positionService.deletePosition(id);
        if (deletePosition) {
            return "Chức vụ đã được xóa mềm (state = false)";
        } else {
            return "Không tìm thấy chức vụ với id: " + id;
        }
    }
}
