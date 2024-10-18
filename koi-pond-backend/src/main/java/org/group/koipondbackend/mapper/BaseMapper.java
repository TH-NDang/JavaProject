package org.group.koipondbackend.mapper;

import java.util.List;

public abstract class BaseMapper <E, D> implements GenericMapper<E, D> {
    @Override
    public List<D> toDto(List<E> entities) {
        return entities.stream().map(this::toDto).toList();
    }

    @Override
    public List<E> toEntity(List<D> dtos) {
        return dtos.stream().map(this::toEntity).toList();
    }
}
