package org.group.koipondbackend.repository;

import org.group.koipondbackend.entity.PondDesign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PondDesignRepository extends JpaRepository<PondDesign, Long> {
}
