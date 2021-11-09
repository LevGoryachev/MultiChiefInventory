package ru.goryachev.multichief.inventory.service;

import ru.goryachev.multichief.inventory.model.dto.CommonDto;

/**
 * ExtendedService provides CRUD operations for common DTO.
 * @author Lev Goryachev
 * @version 1.1
 */
public interface StandardService {
    Iterable<CommonDto> getAll() throws Exception;
    Object create(CommonDto dto) throws Exception;
    Object update(CommonDto dto) throws Exception;
    Object delete(Long id) throws Exception;
}
