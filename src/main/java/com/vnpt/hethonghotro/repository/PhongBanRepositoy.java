package com.vnpt.hethonghotro.repository;

import com.vnpt.hethonghotro.entity.PhongBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface PhongBanRepositoy extends JpaRepository<PhongBan, String> {
    Optional<PhongBan> findByName(String name);

}
