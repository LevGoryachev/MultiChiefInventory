package ru.goryachev.multichief.inventory.model.entity;

import ru.goryachev.multichief.inventory.model.entity.key.AvailabilityCompositeKey;

import javax.persistence.*;


/**
 * Availability - item positions of material which are available in the warehouse, has composite key.
 * @author Lev Goryachev
 * @version 1.1
 */

@Entity
@Table(name = "availability")
@IdClass(AvailabilityCompositeKey.class)
public class Availability {

    @Id
    @ManyToOne
    private Warehouse warehouse;

    @Id
    @ManyToOne
    private Material material;

    @Column(name = "warehouse_qty")
    private Integer qty;     //interface ItemProjection

    public Availability() {
    }

    public Availability(Warehouse warehouse, Material material) {
        this.warehouse = warehouse;
        this.material = material;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    //interface ItemProjection
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
