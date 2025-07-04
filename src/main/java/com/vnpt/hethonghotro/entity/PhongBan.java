package com.vnpt.hethonghotro.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "phong_ban")
@Data // bao gồm setter, getter, toString, equals, hashCode
@NoArgsConstructor // tạo constructor ko tham số
@AllArgsConstructor // tạo constructor có tham số
public class PhongBan {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "name", nullable = false, length = 254)
    private String name;

    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @Column(name = "state", nullable = false)
    private boolean state = true;
}