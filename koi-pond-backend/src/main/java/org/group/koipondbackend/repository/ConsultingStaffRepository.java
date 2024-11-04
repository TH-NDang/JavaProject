package org.group.koipondbackend.repository;

import org.group.koipondbackend.entity.ConsultingStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
<<<<<<< HEAD
public interface ConsultingStaffRepository extends StaffRepository<ConsultingStaff> {
}
=======
public interface ConsultingStaffRepository extends JpaRepository<ConsultingStaff, Long> {
    List<ConsultingStaff> findBySpecialization(String specialization);

    List<ConsultingStaff> findByExperienceYearsGreaterThan(Integer years);
}
>>>>>>> frontend
