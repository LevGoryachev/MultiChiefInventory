package ru.goryachev.multichief.mrp.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Material - catalogue of all materials (for construction)
 */

@Entity
@Table(name = "material")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "um")
    private String um;

    @Column(name = "unit_weight_kg")
    private Integer unit_weight_kg;

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

    public Integer getUnit_weight_kg() {
        return unit_weight_kg;
    }

    public void setUnit_weight_kg(Integer unitweight_kg) {
        this.unit_weight_kg = unitweight_kg;
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
                Objects.equals(getUnit_weight_kg(), material.getUnit_weight_kg()) &&
                Objects.equals(getNotes(), material.getNotes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getUm(), getUnit_weight_kg(), getNotes());
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", um='" + um + '\'' +
                ", unit_weight_kg=" + unit_weight_kg +
                ", notes='" + notes + '\'' +
                '}';
    }
}
