package com.vnpt.hethonghotro.repository;

import com.vnpt.hethonghotro.entity.ChucVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface ChucVuRepository extends JpaRepository<ChucVu, String> {
    Optional<ChucVu> findByName(String name);
}
