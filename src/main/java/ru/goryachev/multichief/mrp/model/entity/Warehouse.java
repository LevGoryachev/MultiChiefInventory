package ru.goryachev.multichief.mrp.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Warehouse of company
 */
@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wh_address")
    private String wh_address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWh_address() {
        return wh_address;
    }

    public void setWh_address(String wh_address) {
        this.wh_address = wh_address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Warehouse)) return false;
        Warehouse warehouse = (Warehouse) o;
        return Objects.equals(getId(), warehouse.getId()) &&
                Objects.equals(getWh_address(), warehouse.getWh_address());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getWh_address());
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", wh_address='" + wh_address + '\'' +
                '}';
    }
}
