package com.vnpt.hethonghotro.dto.RoomDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomSaveDTO {
    private String id;
    private String name;
    private String phone;
    private boolean state = true;
}
