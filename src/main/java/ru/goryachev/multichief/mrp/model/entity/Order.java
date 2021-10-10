package ru.goryachev.multichief.mrp.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Order - a document that refers to items of materials for a construction site (materials are ordered by an employee).
 * @author Lev Goryachev
 * @version 1.1
 */
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_time")
    private LocalDateTime order_time;

    @Column(name = "posted")
    private Boolean posted;

    @Column(name = "sent")
    private Boolean sent;

    @Column(name = "status_executed")
    private Boolean status_executed;

    //field bom_id in process


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrder_time() {
        return order_time;
    }

    public void setOrder_time(LocalDateTime order_time) {
        this.order_time = order_time;
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

    public Boolean getStatus_executed() {
        return status_executed;
    }

    public void setStatus_executed(Boolean status_executed) {
        this.status_executed = status_executed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId()) &&
                Objects.equals(getOrder_time(), order.getOrder_time()) &&
                Objects.equals(getPosted(), order.getPosted()) &&
                Objects.equals(getSent(), order.getSent()) &&
                Objects.equals(getStatus_executed(), order.getStatus_executed());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOrder_time(), getPosted(), getSent(), getStatus_executed());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", order_time=" + order_time +
                ", posted=" + posted +
                ", sent=" + sent +
                ", status_executed=" + status_executed +
                '}';
    }
}
