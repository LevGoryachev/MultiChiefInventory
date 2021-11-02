package ru.goryachev.multichief.inventory.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

/**
 * Bom is a bill of materials (for certain building construction), head of the document
 * @author Lev Goryachev
 * @version 1.1
 */

@Entity
@Table(name = "bom")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Bom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "internal_doc_num")
    private Integer internalDocNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getInternalDocNum() {
        return internalDocNum;
    }

    public void setInternalDocNum(Integer internalDocNum) {
        this.internalDocNum = internalDocNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bom)) return false;
        Bom bom = (Bom) o;
        return Objects.equals(getId(), bom.getId()) &&
                Objects.equals(getInternalDocNum(), bom.getInternalDocNum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getInternalDocNum());
    }

    @Override
    public String toString() {
        return "Bom{" +
                "id=" + id +
                ", internalDocNum=" + internalDocNum +
                '}';
    }
}
