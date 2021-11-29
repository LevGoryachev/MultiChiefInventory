package ru.goryachev.multichief.inventory.model.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * ItemRequestDto is a transfer object for receiving a material item of document (bom (bill of materials) or order or warehouse etc.).
 * ItemRequestDto is used for request body contains id and quantity of material (from client).
 * These data (id and quantity) go to appropriate domain service layer (BOM, ImOrder, Warehouse, etc.).
 * Service layer checks if exists and converts them to other DTOs or entities.
 * For succeeding, the client should choose an existing material (id) and sets qty (material quantity) making request body.
 * @author Lev Goryachev
 * @version 1.1
 */

public class ItemRequestDto {

    private Long materialId;

    @NotNull
    @Min(1)
    @Max(2147000000)
    private Integer qty;

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemRequestDto)) return false;
        ItemRequestDto that = (ItemRequestDto) o;
        return Objects.equals(getMaterialId(), that.getMaterialId()) &&
                Objects.equals(getQty(), that.getQty());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMaterialId(), getQty());
    }
}
