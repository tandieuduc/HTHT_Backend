package com.vnpt.hethonghotro.dto.PositionDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionDTO {
    private String id;
    private String name;
    private boolean state = true;
}
