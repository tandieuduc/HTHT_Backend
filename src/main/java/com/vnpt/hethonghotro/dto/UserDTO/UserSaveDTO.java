package com.vnpt.hethonghotro.dto.UserDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveDTO {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String employeeId;

    @NotBlank
    private String name;
    private Boolean gender;
    private String phone;

    @Email
    @NotBlank
    private String email;
    private String address;

    @NotBlank
    private String phongBanName;

    @NotBlank
    private String chucVuName;

    private List<String> vaiTroNames;
}
