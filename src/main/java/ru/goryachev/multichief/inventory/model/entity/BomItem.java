package ru.goryachev.multichief.inventory.model.entity;

import ru.goryachev.multichief.inventory.model.entity.key.BomItemCompositeKey;

import javax.persistence.*;


/**
 * BomItem - Item position of material (in the bill), has composite key.
 * @author Lev Goryachev
 * @version 1.1
 */

@Entity
@Table(name = "bom_item")
@IdClass(BomItemCompositeKey.class)
public class BomItem {

    @Id
    @ManyToOne
    private Bom bom;

    @Id
    @ManyToOne
    private Material material;

    @Column(name = "bom_qty")
    private Integer qty;     //interface ItemProjection

    public BomItem() {
    }

    public BomItem(Bom bom, Material material) {
        this.bom = bom;
        this.material = material;
    }

    public Bom getBom() {
        return bom;
    }

    public void setBom(Bom bom) {
        this.bom = bom;
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
