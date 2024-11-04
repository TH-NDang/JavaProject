package org.group.koipondbackend.repository;

import org.group.koipondbackend.entity.ConstructionStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDate;

@Repository
public interface ConstructionStaffRepository extends JpaRepository<ConstructionStaff, Long> {
    List<ConstructionStaff> findByCertification(String certification);

    List<ConstructionStaff> findBySafetyTrainingExpiryBefore(LocalDate date);
}
