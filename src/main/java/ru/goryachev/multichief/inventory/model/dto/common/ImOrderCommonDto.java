package ru.goryachev.multichief.inventory.model.dto.common;

import ru.goryachev.multichief.inventory.model.dto.CommonDto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * ImOrderCommonDto is an intermediate object of order (for certain building construction),
 * and it is used for requests and responses (communication between microservices).
 * @author Lev Goryachev
 * @version 1.1
 */

public class ImOrderCommonDto implements CommonDto {

    private Long id;

    @NotNull
    private Long bomId;

    private LocalDateTime orderTime;

    private Boolean posted;

    private Boolean sent;

    private Boolean statusExecuted;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImOrderCommonDto)) return false;
        ImOrderCommonDto that = (ImOrderCommonDto) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getBomId(), that.getBomId()) &&
                Objects.equals(getOrderTime(), that.getOrderTime()) &&
                Objects.equals(getPosted(), that.getPosted()) &&
                Objects.equals(getSent(), that.getSent()) &&
                Objects.equals(getStatusExecuted(), that.getStatusExecuted());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBomId(), getOrderTime(), getPosted(), getSent(), getStatusExecuted());
    }

    @Override
    public String toString() {
        return "ImOrderCommonDto{" +
                "id=" + id +
                ", bomId=" + bomId +
                ", orderTime=" + orderTime +
                ", posted=" + posted +
                ", sent=" + sent +
                ", statusExecuted=" + statusExecuted +
                '}';
    }
}
