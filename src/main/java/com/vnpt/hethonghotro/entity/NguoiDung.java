package com.vnpt.hethonghotro.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data; // Hoặc tự tạo Getters/Setters
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "nguoi_dung")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NguoiDung {

    @Id
    @Column(name = "username", length = 50)
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String username;

    @Column(name = "password", nullable = false, length = 128)
    private String password;

    @Column(name = "employee_id", nullable = false, unique = true, length = 10)
    private String employeeId;

    @Column(name = "name", nullable = false, length = 254)
    private String name;

    @Column(name = "gender", nullable = false)
    private boolean gender;

    @Column(name = "phone", nullable = false, unique = true, length = 15)
    private String phone;

    @Email
    @Column(name = "email", nullable = false, unique = true, length = 254)
    private String email;

    @Column(name = "address", nullable = false, columnDefinition = "TEXT")
    private String address;

    @Lob
    @Column(nullable = true)
    private byte[] avatar;

    @Column(name = "state", nullable = false)
    private Integer state = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_phong_ban", nullable = false)
    private PhongBan phongBan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_chuc_vu", nullable = false)
    private ChucVu chucVu;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "co_vai_tro",
        joinColumns = @JoinColumn(name = "username"),
        inverseJoinColumns = @JoinColumn(name = "id_vai_tro")
    )

    private Set<VaiTro> vaiTros = new HashSet<>();
}