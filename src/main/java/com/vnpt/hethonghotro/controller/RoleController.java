package com.vnpt.hethonghotro.controller;

import com.vnpt.hethonghotro.dto.RoleDTO.RoleDTO;
import com.vnpt.hethonghotro.dto.RoleDTO.RoleSaveDTO;
import com.vnpt.hethonghotro.dto.RoleDTO.RoleUpdateDTO;
import com.vnpt.hethonghotro.entity.VaiTro;
import com.vnpt.hethonghotro.repository.VaiTroRepository;
import com.vnpt.hethonghotro.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private VaiTroRepository vaiTroRepository;

    @GetMapping("/role-name")
    public List<String> getAllVaiTroNames() {
        return vaiTroRepository.findAll().stream()
            .map(VaiTro::getName)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    @GetMapping
    public List<RoleDTO> getAllRole() {
        List<RoleDTO> allRoles = roleService.getAllRole();
        return allRoles;
    }

    @GetMapping("{id}")
    public RoleDTO getById(@PathVariable String id) {
        return roleService.getById(id);
    }

    @PostMapping("/add-role")
    public ResponseEntity<RoleSaveDTO> addRole(@RequestBody @Valid RoleSaveDTO roleSaveDTO)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.addRole(roleSaveDTO));
    }

    @PutMapping("/update-role/{id}")
    public RoleUpdateDTO update(@PathVariable String id, @RequestBody @Valid RoleUpdateDTO roleUpdateDTO) {
        return roleService.updateRole(id, roleUpdateDTO);
    }

    @DeleteMapping("/delete-role/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable String id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
