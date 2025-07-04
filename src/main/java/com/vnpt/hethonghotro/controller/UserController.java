package com.vnpt.hethonghotro.controller;

import com.vnpt.hethonghotro.dto.UserDTO.UserDTO;
import com.vnpt.hethonghotro.dto.UserDTO.UserSaveDTO;
import com.vnpt.hethonghotro.dto.UserDTO.UserUpdateDTO;
import com.vnpt.hethonghotro.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/get-all-user")
    public List<UserDTO> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/get-by-username/{username}")
    public UserDTO getByUsername(@PathVariable String username) {
        return userService.getByUsername(username);
    }

    @PostMapping("/add-user")
    public ResponseEntity<UserSaveDTO> addUser(@RequestBody @Valid UserSaveDTO userSaveDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(userSaveDTO));
    }

    @PutMapping("/update-user/{username}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String username, @RequestBody @Valid UserUpdateDTO userUpdateDTO) {
        return ResponseEntity.ok(userService.updateUser(userUpdateDTO));
    }

    @DeleteMapping("/delete-user/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }
}
