package ru.goryachev.multichief.inventory.model.dto.response;

import ru.goryachev.multichief.inventory.model.dto.PreformDto;
import ru.goryachev.multichief.inventory.model.dto.projection.ItemProjection;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PreformImOrderResponseDto)) return false;
        PreformImOrderResponseDto that = (PreformImOrderResponseDto) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getBomId(), that.getBomId()) &&
                Objects.equals(getOrderTime(), that.getOrderTime()) &&
                Objects.equals(getPosted(), that.getPosted()) &&
                Objects.equals(getSent(), that.getSent()) &&
                Objects.equals(getStatusExecuted(), that.getStatusExecuted()) &&
                Objects.equals(getItems(), that.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBomId(), getOrderTime(), getPosted(), getSent(), getStatusExecuted(), getItems());
    }

    @Override
    public String toString() {
        return "PreformImOrderResponseDto{" +
                "id=" + id +
                ", bomId=" + bomId +
                ", orderTime=" + orderTime +
                ", posted=" + posted +
                ", sent=" + sent +
                ", statusExecuted=" + statusExecuted +
                '}';
    }
}
