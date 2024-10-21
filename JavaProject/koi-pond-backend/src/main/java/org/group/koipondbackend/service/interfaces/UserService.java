package org.group.koipondbackend.service.interfaces;

import org.group.koipondbackend.dto.UserDTO;
import org.group.koipondbackend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService<T extends User, D extends UserDTO> {
    D create(D dto);

    Optional<D> findById(Long id);

    List<D> findAll();

    D update(D dto);

    void delete(Long id);
}
