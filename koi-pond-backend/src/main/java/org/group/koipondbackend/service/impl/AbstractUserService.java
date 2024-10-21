package org.group.koipondbackend.service.impl;

import java.util.List;
import java.util.Optional;

import org.group.koipondbackend.dto.UserDTO;
import org.group.koipondbackend.entity.User;
import org.group.koipondbackend.mapper.GenericMapper;
import org.group.koipondbackend.repository.UserRepository;
import org.group.koipondbackend.service.interfaces.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractUserService<T extends User, D extends UserDTO>
        implements UserService<T, D> {

    protected final UserRepository<T> repository;
    protected final GenericMapper<T, D> mapper;

    @Override
    public D create(D dto) {
        T entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public Optional<D> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public List<D> findAll() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    public D update(D dto) {
        T entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
