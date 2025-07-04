package com.vnpt.hethonghotro.service;

import com.vnpt.hethonghotro.dto.RoleDTO.RoleDTO;
import com.vnpt.hethonghotro.dto.RoleDTO.RoleSaveDTO;
import com.vnpt.hethonghotro.dto.RoleDTO.RoleUpdateDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> getAllRole();
    RoleDTO getById(String id);
    RoleSaveDTO addRole(RoleSaveDTO roleSaveDTO);
    RoleUpdateDTO updateRole(String id, RoleUpdateDTO roleUpdateDTO);
    void deleteRole(String id);
}
