package ru.goryachev.multichief.inventory.service.converter;

import ru.goryachev.multichief.inventory.model.dto.CommonDto;
/**
 * Converter is used for converting Entity to CommonDto and CommonDto to Entity using common fields.
 * @author Lev Goryachev
 * @version 1.1
 */
public interface Converter {
    CommonDto entityToDto (Object o);
    Object dtoToEntity (CommonDto dto);
}
