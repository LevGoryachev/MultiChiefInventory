package ru.goryachev.multichief.mrp.model.keys;

import java.io.Serializable;

/**
 * BomItemKey - composite key class
 * @author Lev Goryachev
 * @version 1.1
 */

public class BomItemCompositeKey implements Serializable {

    Long bomId;

    Long materialId;

    public BomItemCompositeKey() {
    }

    public BomItemCompositeKey(Long bomId, Long materialId) {
        this.bomId = bomId;
        this.materialId = materialId;
    }

    public Long getBom_id() {
        return bomId;
    }

    public void setBom_id(Long bom_id) {
        this.bomId = bom_id;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }
}
