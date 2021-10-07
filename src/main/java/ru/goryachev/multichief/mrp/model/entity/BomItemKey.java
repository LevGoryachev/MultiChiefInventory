package ru.goryachev.multichief.mrp.model.entity;

import org.hibernate.annotations.Tables;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * BomItemKey - composite key class.
 */

@Embeddable
@Table(name = "bom_item")
public class BomItemKey implements Serializable {

    @Column(name = "bom_id")
    Long bom_id;

    @Column(name = "material_id")
    Long material_id;

    public Long getBom_id() {
        return bom_id;
    }

    public void setBom_id(Long bom_id) {
        this.bom_id = bom_id;
    }

    public Long getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(Long material_id) {
        this.material_id = material_id;
    }
}
