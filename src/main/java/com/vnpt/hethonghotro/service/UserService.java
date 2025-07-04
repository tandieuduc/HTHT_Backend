package com.vnpt.hethonghotro.service;

import com.vnpt.hethonghotro.dto.UserDTO.UserDTO;
import com.vnpt.hethonghotro.dto.UserDTO.UserSaveDTO;
import com.vnpt.hethonghotro.dto.UserDTO.UserUpdateDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUser();
    UserDTO getByUsername(String username);
    UserSaveDTO addUser(UserSaveDTO userSaveDTO);
    UserDTO updateUser(UserUpdateDTO userUpdateDTO);
    void deleteUser(String username);
}
