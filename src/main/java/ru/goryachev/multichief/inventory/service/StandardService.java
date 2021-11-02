package ru.goryachev.multichief.inventory.service;

import ru.goryachev.multichief.inventory.model.dto.CommonDto;

/**
 * ExtendedService provides CRUD operations for common DTO.
 * @author Lev Goryachev
 * @version 1.1
 */
public interface StandardService {
    Iterable<CommonDto> getAll();
    Iterable<Long> create(Iterable<CommonDto> dtos);
    Iterable<Long> update(Iterable<CommonDto> dtos);
    void delete(Iterable<Long> set);
}
