package com.vnpt.hethonghotro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vai_tro")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VaiTro {
    @Id
    @Column(name="id", length = 36)
    private String id;

    @Column(name="name" ,nullable = false, length = 50)
    private String name;
}