package ru.goryachev.multichief.inventory.model.entity.key;

import java.io.Serializable;

/**
 * BomItemCompositeKey - composite key class
 * @author Lev Goryachev
 * @version 1.1
 */

public class BomItemCompositeKey implements Serializable {

    Long bom;

    Long material;

    public BomItemCompositeKey() {
    }

    public BomItemCompositeKey(Long bomId, Long materialId) {
        this.bom = bomId;
        this.material = materialId;
    }

    public Long getBom_id() {
        return bom;
    }

    public void setBom_id(Long bom_id) {
        this.bom = bom_id;
    }

    public Long getMaterialId() {
        return material;
    }

    public void setMaterialId(Long materialId) {
        this.material = materialId;
    }
}
