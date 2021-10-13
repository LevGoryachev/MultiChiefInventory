package ru.goryachev.multichief.mrp.model.dto.projection;
/**
 * MaterialView - DTO projection for ResponseDTOs
 * @author Lev Goryachev
 * @version 1.1
 */
public interface MaterialView {
    Long getId();
    String getName();
    String getUm();
    Integer getUnitWeightKg();
    String getNotes();
}
