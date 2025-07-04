package com.vnpt.hethonghotro.dto.UserDTO;

import com.vnpt.hethonghotro.entity.ChucVu;
import com.vnpt.hethonghotro.entity.PhongBan;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {
    private String emplyeeId;
    private String username;
    private String name;
    private Boolean gender;
    private String phone;
    @Email
    private String email;
    private String address;
    private String phongBanName;
    private String chucVuName;
    private Integer state;
    private List<String> vaiTroNames;
}
