package ru.goryachev.multichief.inventory.model.dto.response;

import ru.goryachev.multichief.inventory.model.dto.PreformDto;
import ru.goryachev.multichief.inventory.model.dto.projection.ItemProjection;

import java.util.List;
import java.util.Objects;

/**
 * PreformWarehouseResponseDto is a preform for a presentable document - availability of materials in a warehouse (for certain building construction or object)
 * contains a head of document and list of materials (items).
 * @author Lev Goryachev
 * @version 1.1
 */

public class PreformWarehouseResponseDto implements PreformDto {

    private Long id;

    private String whAddress;

    private List<ItemProjection> items;

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

    public List<ItemProjection> getItems() {
        return items;
    }

    public void setItems(List<ItemProjection> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PreformWarehouseResponseDto)) return false;
        PreformWarehouseResponseDto that = (PreformWarehouseResponseDto) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getWhAddress(), that.getWhAddress()) &&
                Objects.equals(getItems(), that.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getWhAddress(), getItems());
    }

    @Override
    public String toString() {
        return "PreformWarehouseResponseDto{" +
                "id=" + id +
                ", whAddress='" + whAddress + '\'' +
                '}';
    }
}
