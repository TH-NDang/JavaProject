package org.group.koipondbackend.mapper;

import java.util.List;

public interface GenericMapper<E, D> {
    public D toDto(E entity);

    public E toEntity(D dto);

    public default List<D> toDto(List<E> entityList) {
        return entityList.stream().map(this::toDto).toList();
    }

    public default List<E> toEntity(List<D> dtoList) {
        return dtoList.stream().map(this::toEntity).toList();
    }
}
