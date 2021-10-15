package ru.goryachev.multichief.mrp.model.dto;

import javax.persistence.*;

/**
 * MaterialCommonDto is an intermediate object of catalogue unit of all materials (for building construction),
 * and it is used for communication between microservices.
 * @author Lev Goryachev
 * @version 1.1
 */

//@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class MaterialCommonDto {

    private Long id;

    private String name;

    private String um;

    private Integer unitWeightKg;

    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public Integer getUnitWeightKg() {
        return unitWeightKg;
    }

    public void setUnitWeightKg(Integer unit_weight_kg) {
        this.unitWeightKg = unit_weight_kg;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
