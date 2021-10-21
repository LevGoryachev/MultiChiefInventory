package ru.goryachev.multichief.mrp.model.dto.projection;
/**
 * ItemView - DTO projection for ResponseDTOs
 * @author Lev Goryachev
 * @version 1.1
 */
public interface ItemProjection {
    MaterialProjection getMaterial();
    Integer getQty(); // renamed to qty
}
