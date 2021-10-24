package ru.goryachev.multichief.mrp.model.dto.preform;

import ru.goryachev.multichief.mrp.model.dto.PreformDto;
import ru.goryachev.multichief.mrp.model.dto.projection.ItemProjection;

import java.util.List;

/**
 * PreformAvailabilityResponseDto is a preform for a presentable document - availability of materials in a warehouse (for certain building construction or object)
 * contains a head of document and list of materials (items).
 * @author Lev Goryachev
 * @version 1.1
 */

public class PreformAvailabilityResponseDto implements PreformDto {

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
}
