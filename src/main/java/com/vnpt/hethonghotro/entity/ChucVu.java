package com.vnpt.hethonghotro.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chuc_vu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChucVu {
    @Id
    @Column(length = 36)
    private String id;
    private String name;
    @Column(name = "state")
    private boolean state;
}