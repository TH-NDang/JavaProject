package org.group.koipondbackend.repository;

import org.group.koipondbackend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Có thể thêm các phương thức truy vấn tùy chỉnh tại đây
}