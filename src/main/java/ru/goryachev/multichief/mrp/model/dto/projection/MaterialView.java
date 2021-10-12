package ru.goryachev.multichief.mrp.model.dto.projection;

public interface MaterialView {

    Long getId();
    String getName();
    String getUm();
    Integer getUnitWeightKg();
    String getNotes();

}
