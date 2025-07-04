package com.vnpt.hethonghotro.dto.RoomDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private String id;
    private String name;
    private String phone;
    private boolean state = true;

    // Tạo constructor có tham số + constructor ko tham số
    // Tạo setter + getter (toString)
}
