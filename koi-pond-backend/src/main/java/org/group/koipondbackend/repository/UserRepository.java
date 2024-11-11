package org.group.koipondbackend.repository;

import org.group.koipondbackend.entity.User;
import org.group.koipondbackend.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findAllByRole(Role role);

    @Query("SELECT COUNT(u) FROM User u WHERE u.role = 'CUSTOMER'")
    Integer countAllCustomers();

    @Query("SELECT COUNT(u) FROM User u WHERE u.role = 'CUSTOMER' AND u.createdAt BETWEEN :startDate AND :endDate")
    Integer countNewCustomersBetween(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT u FROM User u WHERE u.role = 'CUSTOMER'")
    List<User> findAllCustomers();
}
