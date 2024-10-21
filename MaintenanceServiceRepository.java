package org.group.koipondbackend.repository;

import org.group.koipondbackend.entity.MaintenanceService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceServiceRepository extends JpaRepository<MaintenanceService, Long> {
}
