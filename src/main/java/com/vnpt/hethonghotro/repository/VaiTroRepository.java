package com.vnpt.hethonghotro.repository;

import com.vnpt.hethonghotro.entity.VaiTro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface VaiTroRepository extends JpaRepository<VaiTro, String> {

    // Đây là câu lệnh quan trọng để lấy tất cả vai trò của một người dùng
    @Query(value = "SELECT vt.* FROM vai_tro vt " +
            "JOIN co_vai_tro cvt ON vt.id = cvt.id_vai_tro " +
            "WHERE cvt.username = :username", nativeQuery = true)
    List<VaiTro> findRolesByUsername(@Param("username") String username);

    boolean existsByName(String name);
    List<VaiTro> findByNameIn(List<String> names);
}
