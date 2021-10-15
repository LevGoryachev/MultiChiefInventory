package ru.goryachev.multichief.mrp.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Order - a document that refers to items of materials for a construction site (materials are ordered by an employee).
 * @author Lev Goryachev
 * @version 1.1
 */
@Entity
@Table(name = "im_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bom_id")
    private Long bomId;

    @Column(name = "order_time")
    private LocalDateTime orderTime;

    @Column(name = "posted")
    private Boolean posted;

    @Column(name = "sent")
    private Boolean sent;

    @Column(name = "status_executed")
    private Boolean statusExecuted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBomId() {
        return bomId;
    }

    public void setBomId(Long bomId) {
        this.bomId = bomId;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public Boolean getPosted() {
        return posted;
    }

    public void setPosted(Boolean posted) {
        this.posted = posted;
    }

    public Boolean getSent() {
        return sent;
    }

    public void setSent(Boolean sent) {
        this.sent = sent;
    }

    public Boolean getStatusExecuted() {
        return statusExecuted;
    }

    public void setStatusExecuted(Boolean statusExecuted) {
        this.statusExecuted = statusExecuted;
    }
}
