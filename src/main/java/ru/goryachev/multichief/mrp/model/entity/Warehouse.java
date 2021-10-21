package ru.goryachev.multichief.mrp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

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
}
