package ru.goryachev.multichief.inventory.service;

import ru.goryachev.multichief.inventory.model.dto.PreformDto;
/**
 * PreformService is intended for services that work with PreformDto types (preforms of documents, which can use in other APPs);
 * gets position by its ID and attaches items and other data forming the preform; then returns as PreformDto.
 * @author Lev Goryachev
 * @version 1.1
 */
public interface PreformService {
    PreformDto getPreform (Long id) throws Exception;
}
