package ru.goryachev.multichief.inventory.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

/**
 * Material - catalogue unit of all materials (for building construction)
 * @author Lev Goryachev
 * @version 1.1
 */

@Entity
@Table(name = "material")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "um")
    private String um;

    @Column(name = "unit_weight_kg")
    private Integer unitWeightKg;

    @Column(name = "notes")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Material)) return false;
        Material material = (Material) o;
        return Objects.equals(getId(), material.getId()) &&
                Objects.equals(getName(), material.getName()) &&
                Objects.equals(getUm(), material.getUm()) &&
                Objects.equals(getUnitWeightKg(), material.getUnitWeightKg()) &&
                Objects.equals(getNotes(), material.getNotes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getUm(), getUnitWeightKg(), getNotes());
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", um='" + um + '\'' +
                ", unitWeightKg=" + unitWeightKg +
                ", notes='" + notes + '\'' +
                '}';
    }
}
