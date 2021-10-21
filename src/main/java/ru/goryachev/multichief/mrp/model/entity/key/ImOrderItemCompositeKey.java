package ru.goryachev.multichief.mrp.model.entity.key;

import java.io.Serializable;

/**
 * ImOrderItemCompositeKey - composite key class
 * @author Lev Goryachev
 * @version 1.1
 */

public class ImOrderItemCompositeKey implements Serializable {

    Long bom;

    Long material;

    Long imOrder;

    public ImOrderItemCompositeKey() {
    }

    public ImOrderItemCompositeKey(Long bom, Long material, Long imOrder) {
        this.bom = bom;
        this.material = material;
        this.imOrder = imOrder;
    }

    public Long getBom() {
        return bom;
    }

    public void setBom(Long bom) {
        this.bom = bom;
    }

    public Long getMaterial() {
        return material;
    }

    public void setMaterial(Long material) {
        this.material = material;
    }

    public Long getImOrder() {
        return imOrder;
    }

    public void setImOrder(Long imOrder) {
        this.imOrder = imOrder;
    }
}
