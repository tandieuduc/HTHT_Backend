package com.vnpt.hethonghotro.service;

import com.vnpt.hethonghotro.dto.UserDTO.UserDTO;
import com.vnpt.hethonghotro.dto.UserDTO.UserSaveDTO;
import com.vnpt.hethonghotro.dto.UserDTO.UserUpdateDTO;
import com.vnpt.hethonghotro.entity.ChucVu;
import com.vnpt.hethonghotro.entity.NguoiDung;
import com.vnpt.hethonghotro.entity.PhongBan;
import com.vnpt.hethonghotro.entity.VaiTro;
import com.vnpt.hethonghotro.exception.NotFoundException;
import com.vnpt.hethonghotro.repository.ChucVuRepository;
import com.vnpt.hethonghotro.repository.NguoiDungRepository;
import com.vnpt.hethonghotro.repository.PhongBanRepositoy;
import com.vnpt.hethonghotro.repository.VaiTroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceIMPL implements UserService{

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private PhongBanRepositoy phongBanRepositoy;

    @Autowired
    private VaiTroRepository vaiTroRepository;

    @Autowired
    private ChucVuRepository chucVuRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getAllUser() {
        return nguoiDungRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public UserDTO getByUsername(String username) {
        NguoiDung user = nguoiDungRepository.findById(username)
            .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng"));
        return convertToDTO(user);
    }

    @Override
    public UserSaveDTO addUser(UserSaveDTO userSaveDTO) {
        PhongBan phongBan = phongBanRepositoy.findByName(userSaveDTO.getPhongBanName())
            .orElseThrow(() -> new NotFoundException("Không tìm thấy phòng ban: " + userSaveDTO.getPhongBanName()));

        ChucVu chucVu = chucVuRepository.findByName(userSaveDTO.getChucVuName())
            .orElseThrow(() -> new NotFoundException("Không tìm thấy chức vụ: " + userSaveDTO.getChucVuName()));


        if (nguoiDungRepository.existsById(userSaveDTO.getUsername())) {
            throw new IllegalArgumentException("Tên đăng nhập đã tồn tại");
        }

        Set<VaiTro> vaiTros = new HashSet<>(vaiTroRepository.findByNameIn(userSaveDTO.getVaiTroNames()));

        if (vaiTros.size() != userSaveDTO.getVaiTroNames().size())
        {
            throw new IllegalArgumentException("Một hoặc nhiều vai trò không hợp lệ");
        }
        NguoiDung user = NguoiDung.builder()
            .username(userSaveDTO.getUsername())
            .password(passwordEncoder.encode(userSaveDTO.getPassword()))
            .employeeId(userSaveDTO.getEmployeeId())
            .name(userSaveDTO.getName())
            .gender(userSaveDTO.getGender())
            .phone(userSaveDTO.getPhone())
            .email(userSaveDTO.getEmail())
            .address(userSaveDTO.getAddress())
            .state(1)
            .phongBan(phongBan)
            .chucVu(chucVu)
            .vaiTros(vaiTros)
            .build();

        nguoiDungRepository.save(user);
        return userSaveDTO;
    }

    @Override
    public UserDTO updateUser(UserUpdateDTO userUpdateDTO) {
        NguoiDung user = nguoiDungRepository.findById(userUpdateDTO.getUsername())
            .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng"));

        user.setName(userUpdateDTO.getName());
        user.setGender(userUpdateDTO.getGender());
        user.setPhone(userUpdateDTO.getPhone());
        user.setEmail(userUpdateDTO.getEmail());
        user.setAddress(userUpdateDTO.getAddress());
        user.setState(userUpdateDTO.getState());

        // Cập nhật phòng ban nếu có
        if (userUpdateDTO.getPhongBanName() != null) {
            PhongBan phongBan = phongBanRepositoy.findByName(userUpdateDTO.getPhongBanName())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy phòng ban"));
            user.setPhongBan(phongBan);
        }

        // Cập nhật chức vụ nếu có
        if (userUpdateDTO.getChucVuName() != null)
        {
            ChucVu chucVu = chucVuRepository.findByName(userUpdateDTO.getChucVuName())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy chức vụ"));
            user.setChucVu(chucVu);
        }
        // Cập nhật vai trò nếu có
        if (userUpdateDTO.getVaiTroNames() != null && !userUpdateDTO.getVaiTroNames().isEmpty()) {
            System.out.println("Vai trò gửi lên: " + userUpdateDTO.getVaiTroNames());

            Set<VaiTro> vaiTros = new HashSet<>(vaiTroRepository.findByNameIn(userUpdateDTO.getVaiTroNames()));
            System.out.println("Vai trò tìm được: " + vaiTros.stream().map(VaiTro::getName).toList());

            if (vaiTros.size() != userUpdateDTO.getVaiTroNames().size()) {
                throw new IllegalArgumentException("Một hoặc nhiều vai trò không hợp lệ");
            }
            user.setVaiTros(vaiTros);
        }

        nguoiDungRepository.save(user);
        System.out.println("Vai trò gửi lên: " + userUpdateDTO.getVaiTroNames());

        return convertToDTO(user);
    }

    @Override
    public void deleteUser(String username) {
        NguoiDung user = nguoiDungRepository.findById(username)
            .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng"));
        user.setState(-1);
        nguoiDungRepository.save(user);
    }

    private UserDTO convertToDTO(NguoiDung user) {
        System.out.println("Vai trò tìm được: " + user.getVaiTros().stream().map(VaiTro::getName).toList());
        return new UserDTO(
                    user.getEmployeeId(),
                    user.getUsername(),
                    user.getName(),
                    user.isGender(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getAddress(),
                    user.getPhongBan() != null ? user.getPhongBan().getName() : null,
                    user.getChucVu() != null ? user.getChucVu().getName() : null,
                    user.getState(),
                    user.getVaiTros().stream().map(VaiTro::getName).toList()
                );
    }
}
