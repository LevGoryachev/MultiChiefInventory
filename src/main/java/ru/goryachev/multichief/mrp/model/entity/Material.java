package ru.goryachev.multichief.mrp.model.entity;

import javax.persistence.*;

/**
 * Material - catalogue unit of all materials (for building construction)
 * @author Lev Goryachev
 * @version 1.1
 */

@Entity
@Table(name = "material")
//@SecondaryTable(name = "bom_item", schema = "public", pkJoinColumns = {@PrimaryKeyJoinColumn (name = "material_id", referencedColumnName = "id")})
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

    /*@OneToMany(*//*cascade = CascadeType.ALL, fetch = FetchType.LAZY,*//* mappedBy = "material_id")
    private Set<Bom> boms;*/

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
