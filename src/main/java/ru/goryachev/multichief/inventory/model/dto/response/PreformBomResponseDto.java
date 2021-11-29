package ru.goryachev.multichief.inventory.model.dto.response;

import ru.goryachev.multichief.inventory.model.dto.PreformDto;
import ru.goryachev.multichief.inventory.model.dto.projection.ItemProjection;

import java.util.List;
import java.util.Objects;

/**
 * PreformBomResponseDto is a preform for a presentable document - bill of materials (for certain building construction or object)
 * contains a head of document and list of materials (items).
 * @author Lev Goryachev
 * @version 1.1
 */

public class PreformBomResponseDto implements PreformDto {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PreformBomResponseDto)) return false;
        PreformBomResponseDto that = (PreformBomResponseDto) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getInternalDocNum(), that.getInternalDocNum()) &&
                Objects.equals(getItems(), that.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getInternalDocNum(), getItems());
    }

    @Override
    public String toString() {
        return "PreformBomResponseDto{" +
                "id=" + id +
                ", internalDocNum=" + internalDocNum +
                '}';
    }
}
