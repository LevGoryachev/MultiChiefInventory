package ru.goryachev.multichief.mrp.model.entity;

import javax.persistence.*;

/**
 * Bom - Bill of materials (for certain building construction), head of the document
 * @author Lev Goryachev
 * @version 1.1
 */
@Entity
@Table(name = "bom")
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
}
