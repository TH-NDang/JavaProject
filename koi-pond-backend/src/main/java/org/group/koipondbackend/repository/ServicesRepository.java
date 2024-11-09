package org.group.koipondbackend.repository;

import org.group.koipondbackend.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long>, JpaSpecificationExecutor<Services> {
    List<Services> findByIsPopularTrue();

    boolean existsByName(String name);
}
