package org.group.koipondbackend.repository;

import org.group.koipondbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository<T extends User> extends JpaRepository<T, Long> {
}
