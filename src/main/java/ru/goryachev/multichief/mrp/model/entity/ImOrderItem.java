package ru.goryachev.multichief.mrp.model.entity;

import ru.goryachev.multichief.mrp.model.entity.key.ImOrderItemCompositeKey;

import javax.persistence.*;


/**
 * ImOrderItem - Item position of material (in the internal material order), has composite key.
 * @author Lev Goryachev
 * @version 1.1
 */

@Entity
@Table(name = "im_order_item")
@IdClass(ImOrderItemCompositeKey.class)
public class ImOrderItem {

    @Id
    @ManyToOne
    private Bom bom;

    @Id
    @ManyToOne
    private Material material;

    @Id
    @ManyToOne
    private ImOrder imOrder;

    @Column(name = "status_delivered", insertable=false)
    private Boolean statusDelivered;

    @Column(name = "im_order_qty")
    private Integer qty;     //interface ItemProjection

    public ImOrderItem() {
    }

    public ImOrderItem(Bom bom, Material material, ImOrder imOrder) {
        this.bom = bom;
        this.material = material;
        this.imOrder = imOrder;
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

    public ImOrder getImOrder() {
        return imOrder;
    }

    public void setImOrder(ImOrder imOrder) {
        this.imOrder = imOrder;
    }

    public Boolean getStatusDelivered() {
        return statusDelivered;
    }

    public void setStatusDelivered(Boolean statusDelivered) {
        this.statusDelivered = statusDelivered;
    }

    //interface ItemProjection
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
