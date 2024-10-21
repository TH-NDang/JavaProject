package org.group.koipondbackend.repository;

import org.group.koipondbackend.entity.KoiPond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KoiPondRepository extends JpaRepository<KoiPond, Long> {
}
