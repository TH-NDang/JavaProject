package org.group.koipondbackend.repository;

import org.group.koipondbackend.entity.ConsultingStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultingStaffRepository extends JpaRepository<ConsultingStaff, Long> {
    List<ConsultingStaff> findBySpecialization(String specialization);

    List<ConsultingStaff> findByExperienceYearsGreaterThan(Integer years);
}
