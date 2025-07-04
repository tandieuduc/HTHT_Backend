package com.vnpt.hethonghotro.service;

import com.vnpt.hethonghotro.dto.PositionDTO.PositionDTO;
import com.vnpt.hethonghotro.dto.PositionDTO.PositionSaveDTO;
import com.vnpt.hethonghotro.dto.PositionDTO.PositionUpdateDTO;
import com.vnpt.hethonghotro.entity.ChucVu;
import com.vnpt.hethonghotro.entity.PhongBan;
import com.vnpt.hethonghotro.repository.ChucVuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PositionServiceIMPL implements PositionService{

    @Autowired
    private ChucVuRepository chucVuRepository;

    @Override
    public List<PositionDTO> getAllPosition()
    {
        List<ChucVu> getPositions = chucVuRepository.findAll();
        List<PositionDTO> positionDTOList = new ArrayList<>();
        for (ChucVu c:getPositions) {
            PositionDTO positionDTO = new PositionDTO(
                c.getId(),
                c.getName(),
                c.isState()
            );
            positionDTOList.add(positionDTO);
        }
        return positionDTOList;
    }

    @Override
    public String addPosition(PositionSaveDTO positionSaveDTO)
    {
        ChucVu chucVu = new ChucVu(
            positionSaveDTO.getId(),
            positionSaveDTO.getName(),
            true
        );
        if (chucVuRepository.existsById(positionSaveDTO.getId())) {
            throw new RuntimeException("ID chức vụ đã tồn tại!");
        }
        System.out.println("🟢 ID nhận vào: " + positionSaveDTO.getId());
        chucVuRepository.save(chucVu);
        System.out.println("Thêm mới chức vụ thành công");
        return chucVu.getName();
    }

    @Override
    public String updatePosition(PositionUpdateDTO positionUpdateDTO) {
        if (chucVuRepository.existsById(positionUpdateDTO.getId())) {
            ChucVu chucVu = chucVuRepository.getById(positionUpdateDTO.getId());
            chucVu.setName(positionUpdateDTO.getName());
            chucVu.setState(positionUpdateDTO.isState());
            chucVuRepository.save(chucVu);
            System.out.println("Cập nhật chức vụ hoàn tất");
            return chucVu.getName();
        }
        else {
            System.out.println("Mã số chức vụ không tìm thấy");
        }
        return null;
    }

    @Override
    public boolean deletePosition(String id) {
        if (chucVuRepository.existsById(id))
        {
            ChucVu chucVu = chucVuRepository.getById(id);
            chucVu.setState(false);
            chucVuRepository.save(chucVu);
//            System.out.println("Đã xóa mềm chức vụ");
            return true;
        }
        else {
//            System.out.println("Chức vụ không tìm thấy");
            return false;
        }
    }

    @Override
    public boolean deletePositionForever(String id) {
        if (chucVuRepository.existsById(id)) {
            ChucVu chucVu = chucVuRepository.getById(id);
            chucVuRepository.deleteById(id);
            System.out.println("Đã xóa chức vụ trong CSDL");
            return true;
        }
        else {
            System.out.println("Không tìm thấy chức vụ");
            return false;
        }
    }
}
