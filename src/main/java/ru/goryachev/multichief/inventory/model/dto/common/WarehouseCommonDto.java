package ru.goryachev.multichief.inventory.model.dto.common;

/**
 * WarehouseCommonDto is an intermediate object of warehouses of company,
 * and it is used for requests and responses (communication between microservices).
 * @author Lev Goryachev
 * @version 1.1
 */

public class WarehouseCommonDto {

    private Long id;

    private String whAddress;

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
}
