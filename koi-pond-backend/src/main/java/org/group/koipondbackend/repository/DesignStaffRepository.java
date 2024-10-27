package org.group.koipondbackend.repository;

import org.group.koipondbackend.entity.DesignStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DesignStaffRepository extends JpaRepository<DesignStaff, Long> {
    List<DesignStaff> findByDesignSpecialization(String specialization);
}
