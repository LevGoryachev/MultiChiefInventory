package ru.goryachev.multichief.mrp.model.dto.request;

/**
 * ItemRequestDto is a transfer object for receiving a material item of document (bom (bill of materials) or order or warehouse etc.).
 * ItemRequestDto is used for request body to get an id and quantity of material from client (API). These data (id and quantity) go to appropriate Service layer.
 * Service layer checks if exist and converts them to other DTOs or entities.
 * The client chooses an existing material (id) and sets qty (material quantity) making request body.
 * @author Lev Goryachev
 * @version 1.1
 */

public class ItemRequestDto {

    private Long materialId;

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
}
