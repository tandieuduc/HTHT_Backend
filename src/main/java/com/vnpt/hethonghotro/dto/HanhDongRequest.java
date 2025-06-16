package com.vnpt.hethonghotro.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HanhDongRequest {
    // Không cần username vì chúng ta sẽ lấy từ JWT token của người đang đăng nhập
    @NotBlank(message = "Hành động không được để trống")
    private String hanhDong; // "tiep_nhan", "bo_qua", "phan_hoi"
    private String noiDungPhanHoi;
}