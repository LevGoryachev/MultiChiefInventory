package ru.goryachev.multichief.mrp.model.dto.response;

import ru.goryachev.multichief.mrp.model.dto.PreformDto;
import ru.goryachev.multichief.mrp.model.dto.projection.ItemProjection;

import java.time.LocalDateTime;
import java.util.List;

/**
 * PreformImOrderResponseDto is a preform for a presentable document - internal material order (for certain building construction or object)
 * contains a head of document and list of materials (items).
 * @author Lev Goryachev
 * @version 1.1
 */

public class PreformImOrderResponseDto implements PreformDto {

    private Long id;

    private Long bomId;

    private LocalDateTime orderTime;

    private Boolean posted;

    private Boolean sent;

    private Boolean statusExecuted;

    private List<ItemProjection> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBomId() {
        return bomId;
    }

    public void setBomId(Long bomId) {
        this.bomId = bomId;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public Boolean getPosted() {
        return posted;
    }

    public void setPosted(Boolean posted) {
        this.posted = posted;
    }

    public Boolean getSent() {
        return sent;
    }

    public void setSent(Boolean sent) {
        this.sent = sent;
    }

    public Boolean getStatusExecuted() {
        return statusExecuted;
    }

    public void setStatusExecuted(Boolean statusExecuted) {
        this.statusExecuted = statusExecuted;
    }

    public List<ItemProjection> getItems() {
        return items;
    }

    public void setItems(List<ItemProjection> items) {
        this.items = items;
    }
}
