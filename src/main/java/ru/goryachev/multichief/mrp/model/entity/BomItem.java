package ru.goryachev.multichief.mrp.model.entity;

import ru.goryachev.multichief.mrp.model.key.BomItemCompositeKey;

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

    /*@Id
    @Column (name = "bom_id")
    private Long bomId;*/

    /*@Id
    @Column (name = "material_id", insertable = false, updatable = false)
    private Long materialId;*/

    @Column(name = "bom_qty")
    private Integer bomQty;     //rename to Qty for projection

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

    public Integer getBomQty() {
        return bomQty;
    }

    public void setBomQty(Integer bomQty) {
        this.bomQty = bomQty;
    }
}
