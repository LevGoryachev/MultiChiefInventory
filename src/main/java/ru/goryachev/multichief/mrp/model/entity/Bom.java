package ru.goryachev.multichief.mrp.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * BOM - Bill of materials (for certain building construction)
 */
@Entity
@Table(name = "bom")
public class Bom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "internal_doc_num")
    private Integer internal_doc_num;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getInternal_doc_num() {
        return internal_doc_num;
    }

    public void setInternal_doc_num(Integer internal_doc_num) {
        this.internal_doc_num = internal_doc_num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bom)) return false;
        Bom bom = (Bom) o;
        return Objects.equals(getId(), bom.getId()) &&
                Objects.equals(getInternal_doc_num(), bom.getInternal_doc_num());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getInternal_doc_num());
    }

    @Override
    public String toString() {
        return "Bom{" +
                "id=" + id +
                ", internal_doc_num=" + internal_doc_num +
                '}';
    }
}
