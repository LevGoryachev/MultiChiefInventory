package ru.goryachev.multichief.mrp.model.entity;

import javax.persistence.*;

/**
 * BomItem - Item-position of material (in the bill), has composite key
 */
@Entity
@Table(name = "bom_item")
public class BomItem {

    @EmbeddedId
    BomItemKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bom_id")
    @JoinColumn(name = "bom_id")
    private Bom bom;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("material_id")
    @JoinColumn(name = "material_id")
    private Material material;

    @Column(name = "bom_qty")
    private Long bom_qty;

    public BomItemKey getId() {
        return id;
    }

    public void setId(BomItemKey id) {
        this.id = id;
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

    public Long getBom_qty() {
        return bom_qty;
    }

    public void setBom_qty(Long bom_qty) {
        this.bom_qty = bom_qty;
    }
}
