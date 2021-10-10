package ru.goryachev.multichief.mrp.model.dto;

/**
 * ItemDTO - Item - position of material (in the document: bom (bill of materials), order or warehouse list
 * ItemDTO is used to get an existing id and quantity of material and transfer them to appropriate Service
 * It is possible to save the position with material_id if only the id is available on Material table (cataloque).
 * The user sets bom_qty (bill of materials quantity).
 * @author Lev Goryachev
 * @version 1.1
 */

public class ItemDto {

    private Long materialId;

    private Integer bomQty;

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Integer getBomQty() {
        return bomQty;
    }

    public void setBomQty(Integer bomQty) {
        this.bomQty = bomQty;
    }
}
