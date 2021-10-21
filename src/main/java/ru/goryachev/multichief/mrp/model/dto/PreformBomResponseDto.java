package ru.goryachev.multichief.mrp.model.dto;

import ru.goryachev.multichief.mrp.model.dto.projection.ItemProjection;

import java.util.List;

/**
 * BomDto is a preform for a presentable document - bill of materials (for certain building construction or object)
 * contains a head of document and list of materials (items).
 * @author Lev Goryachev
 * @version 1.1
 */

public class PreformBomResponseDto {

    private Long id;

    private Integer internalDocNum;

    private List<ItemProjection> items;

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

    public List<ItemProjection> getItems() {
        return items;
    }

    public void setItems(List<ItemProjection> items) {
        this.items = items;
    }
}
