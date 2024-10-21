package org.group.koipondbackend.repository;

import org.group.koipondbackend.entity.KoiFish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KoiFishRepository extends JpaRepository<KoiFish, Long> {
    // Tìm tất cả cá Koi theo ID hồ
    List<KoiFish> findByPondId(Long pondId);
}
