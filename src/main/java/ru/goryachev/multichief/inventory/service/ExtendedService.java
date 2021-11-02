package ru.goryachev.multichief.inventory.service;

import ru.goryachev.multichief.inventory.model.dto.CommonDto;

/**
 * ExtendedService provides CRUD operations for common DTO.
 * @author Lev Goryachev
 * @version 1.1
 */
public interface ExtendedService {
    Iterable<CommonDto> getAll();
    Iterable<CommonDto> getAllById(Iterable<Long> set);
    CommonDto getById(Long id);
    Iterable<Long> create(Iterable<CommonDto> dtos);
    Iterable<Long> update(Iterable<CommonDto> dtos);
    void delete(Iterable<Long> set);
}
