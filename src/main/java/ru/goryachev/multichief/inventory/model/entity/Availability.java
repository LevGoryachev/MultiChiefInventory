package ru.goryachev.multichief.inventory.model.entity;

import ru.goryachev.multichief.inventory.model.entity.key.AvailabilityCompositeKey;

import javax.persistence.*;
import java.util.Objects;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Availability)) return false;
        Availability that = (Availability) o;
        return Objects.equals(getWarehouse(), that.getWarehouse()) &&
                Objects.equals(getMaterial(), that.getMaterial()) &&
                Objects.equals(getQty(), that.getQty());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWarehouse(), getMaterial(), getQty());
    }

    @Override
    public String toString() {
        return "Availability{" +
                "warehouse=" + warehouse +
                ", material=" + material +
                ", qty=" + qty +
                '}';
    }
}
