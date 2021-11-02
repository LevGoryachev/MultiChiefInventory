package ru.goryachev.multichief.inventory.model.dto.common;

/**
 * BomCommonDto is an intermediate object of bill of materials (for certain building construction), head of the document,
 * and it is used for requests and responses (communication between microservices).
 * @author Lev Goryachev
 * @version 1.1
 */

//@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class BomCommonDto {

    private Long id;

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
