package ru.goryachev.multichief.inventory.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

/**
 * Warehouse - a document that refers to items of materials that are available in the warehouse of company.
 * @author Lev Goryachev
 * @version 1.1
 */
@Entity
@Table(name = "warehouse")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wh_address")
    private String whAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWhAddress() {
        return whAddress;
    }

    public void setWhAddress(String whAddress) {
        this.whAddress = whAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Warehouse)) return false;
        Warehouse warehouse = (Warehouse) o;
        return Objects.equals(getId(), warehouse.getId()) &&
                Objects.equals(getWhAddress(), warehouse.getWhAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getWhAddress());
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", whAddress='" + whAddress + '\'' +
                '}';
    }
}
