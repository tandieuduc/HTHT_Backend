package com.vnpt.hethonghotro.dto.UserDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String employeeId;
    private String username;
    private String name;
    private boolean gender;
    private String email;
    private String phone;
    private String address;
    private String phongBanName;
    private String chucVuName;
    private Integer state;
    private List<String> vaiTroNames; // Danh sách ID vai trò được gán
}
