package com.vnpt.hethonghotro.service;

import com.vnpt.hethonghotro.dto.RoleDTO.RoleDTO;
import com.vnpt.hethonghotro.dto.RoleDTO.RoleSaveDTO;
import com.vnpt.hethonghotro.dto.RoleDTO.RoleUpdateDTO;
import com.vnpt.hethonghotro.entity.VaiTro;
import com.vnpt.hethonghotro.exception.NotFoundException;
import com.vnpt.hethonghotro.repository.VaiTroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceIMPL implements RoleService{

    @Autowired
    private VaiTroRepository vaiTroRepository;

    @Override
    public List<RoleDTO> getAllRole() {
        return vaiTroRepository.findAll().stream()
            .map(vt -> new RoleDTO(vt.getId(), vt.getName()))
            .collect(Collectors.toList());
    }

    @Override
    public RoleDTO getById(String id) {
        VaiTro vt = vaiTroRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Không tìm thấy vai trò"));
        return new RoleDTO(vt.getId(), vt.getName());
    }

    @Override
    public RoleSaveDTO addRole(RoleSaveDTO roleSaveDTO) {
        if (vaiTroRepository.existsById(roleSaveDTO.getId()))
        {
            throw new IllegalArgumentException("ID vai trò đã tồn tại");
        }
        if (vaiTroRepository.existsByName(roleSaveDTO.getName())) {
            throw new IllegalArgumentException("Tên vai trò đã tồn tại");
        }

        VaiTro vt = VaiTro.builder()
            .id(roleSaveDTO.getId())
            .name(roleSaveDTO.getName())
            .build();

        VaiTro saved = vaiTroRepository.save(vt);
        return new RoleSaveDTO(saved.getId(), saved.getName());
    }

    @Override
    public RoleUpdateDTO updateRole(String id, RoleUpdateDTO roleUpdateDTO) {
        VaiTro vt = vaiTroRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Không tìm thấy vai trò"));
        vt.setName(roleUpdateDTO.getName());
        return new RoleUpdateDTO(vaiTroRepository.save(vt).getId(), vt.getName());
    }

    @Override
    public void deleteRole(String id) {
        if (!vaiTroRepository.existsById(id)) {
            throw new NotFoundException("Không tìm thấy vai trò");
        }
        vaiTroRepository.deleteById(id);
    }
}
