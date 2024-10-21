package org.group.koipondbackend.repository;

import org.group.koipondbackend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findTop5ByOrderByTotalSpentDesc(); // Lấy 5 khách hàng hàng đầu theo tổng chi tiêu
}
