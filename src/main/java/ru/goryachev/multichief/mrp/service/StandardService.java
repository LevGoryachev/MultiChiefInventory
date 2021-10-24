package ru.goryachev.multichief.mrp.service;

import ru.goryachev.multichief.mrp.model.dto.CommonDto;

/**
 * StandardService provides CRUD operations.
 * @author Lev Goryachev
 * @version 1.1
 */
public interface StandardService {
    Iterable<CommonDto> getAll();
    Iterable<CommonDto> getAllById(Iterable<Long> set);
    CommonDto getById(Long id);
    Iterable<Long> create(Iterable<CommonDto> dtos);
    Iterable<Long> update(Iterable<CommonDto> dtos);
    void delete(Iterable<Long> set);
}
