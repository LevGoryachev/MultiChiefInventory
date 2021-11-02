package ru.goryachev.multichief.inventory.model.dto.common;

import java.time.LocalDateTime;

/**
 * ImOrderCommonDto is an intermediate object of order (for certain building construction),
 * and it is used for requests and responses (communication between microservices).
 * @author Lev Goryachev
 * @version 1.1
 */

public class ImOrderCommonDto {

    private Long id;

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
}
