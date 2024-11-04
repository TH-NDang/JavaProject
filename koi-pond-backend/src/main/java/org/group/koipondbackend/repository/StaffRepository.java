package org.group.koipondbackend.repository;

import java.util.List;

import org.group.koipondbackend.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    List<Staff> findByDepartment(String department);

    List<Staff> findByStatus(String status);

    List<Staff> findByDepartmentAndStatus(String department, String status);

    @Query("SELECT s FROM Staff s WHERE " +
            "(LOWER(s.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(s.email) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:department IS NULL OR s.department = :department) " +
            "AND (:status IS NULL OR s.status = :status)")
    List<Staff> search(
            @Param("keyword") String keyword,
            @Param("department") String department,
            @Param("status") String status);
}
