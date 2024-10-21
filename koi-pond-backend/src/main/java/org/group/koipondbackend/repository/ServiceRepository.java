package org.group.koipondbackend.repository;

import org.group.koipondbackend.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    // Có thể thêm các phương thức truy vấn tùy chỉnh tại đây
}