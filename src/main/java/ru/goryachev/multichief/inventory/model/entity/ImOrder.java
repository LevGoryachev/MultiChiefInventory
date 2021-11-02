package ru.goryachev.multichief.inventory.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * ImOrder is an internal material order that refers to items of materials for a construction site (materials are ordered by an employee).
 * @author Lev Goryachev
 * @version 1.1
 */
@Entity
@Table(name = "im_order")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class ImOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bom_id")
    private Long bomId;

    @Column(name = "im_order_time")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImOrder)) return false;
        ImOrder imOrder = (ImOrder) o;
        return Objects.equals(getId(), imOrder.getId()) &&
                Objects.equals(getBomId(), imOrder.getBomId()) &&
                Objects.equals(getOrderTime(), imOrder.getOrderTime()) &&
                Objects.equals(getPosted(), imOrder.getPosted()) &&
                Objects.equals(getSent(), imOrder.getSent()) &&
                Objects.equals(getStatusExecuted(), imOrder.getStatusExecuted());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBomId(), getOrderTime(), getPosted(), getSent(), getStatusExecuted());
    }

    @Override
    public String toString() {
        return "ImOrder{" +
                "id=" + id +
                ", bomId=" + bomId +
                ", orderTime=" + orderTime +
                ", posted=" + posted +
                ", sent=" + sent +
                ", statusExecuted=" + statusExecuted +
                '}';
    }
}
