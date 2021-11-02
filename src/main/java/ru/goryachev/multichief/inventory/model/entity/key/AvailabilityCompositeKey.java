package ru.goryachev.multichief.inventory.model.entity.key;


import java.io.Serializable;

/**
 * AvailabilityCompositeKey - composite key class
 * @author Lev Goryachev
 * @version 1.1
 */

public class AvailabilityCompositeKey implements Serializable {

    Long warehouse;

    Long material;

    public AvailabilityCompositeKey() {
    }

    public AvailabilityCompositeKey(Long warehouseId, Long materialId) {
        this.warehouse = warehouseId;
        this.material = materialId;
    }

    public Long getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Long warehouse) {
        this.warehouse = warehouse;
    }

    public Long getMaterial() {
        return material;
    }

    public void setMaterial(Long material) {
        this.material = material;
    }
}
