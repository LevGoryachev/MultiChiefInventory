package ru.goryachev.multichief.mrp.model.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * BOM - Bill of materials (for certain building construction)
 */
@Entity
@Table(name = "bom")
//@SecondaryTable(name = "bom_item", schema = "public", pkJoinColumns = {@PrimaryKeyJoinColumn (name = "bom_id", referencedColumnName = "id")})
public class Bom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "internal_doc_num")
    private Integer internal_doc_num;

   /* @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "bom_item", joinColumns = @JoinColumn(name = "bom_id"*//*, referencedColumnName = "id"*//*), inverseJoinColumns = @JoinColumn(name = "material_id"))
    private Set<MaterialOld> items;*/

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

}
