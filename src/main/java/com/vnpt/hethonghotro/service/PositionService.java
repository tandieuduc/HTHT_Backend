package com.vnpt.hethonghotro.service;

import com.vnpt.hethonghotro.dto.PositionDTO.PositionDTO;
import com.vnpt.hethonghotro.dto.PositionDTO.PositionSaveDTO;
import com.vnpt.hethonghotro.dto.PositionDTO.PositionUpdateDTO;

import java.util.List;

public interface PositionService {
    List<PositionDTO> getAllPosition();

    String addPosition(PositionSaveDTO positionSaveDTO);

    String updatePosition(PositionUpdateDTO positionUpdateDTO);

    boolean deletePosition(String id);

    boolean deletePositionForever(String id);
}
