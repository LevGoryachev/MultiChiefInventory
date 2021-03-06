package ru.goryachev.multichief.inventory.model.dto.common;

import ru.goryachev.multichief.inventory.model.dto.CommonDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * WarehouseCommonDto is an intermediate object of warehouses of company,
 * and it is used for requests and responses (communication between microservices).
 * @author Lev Goryachev
 * @version 1.1
 */

public class WarehouseCommonDto implements CommonDto {

    private Long id;

    @NotBlank
    @Size(min=2, max=220)
    @Pattern(regexp = "(\\S+\\s?\\S+)+", message = "must match: 'word/space/word/space/word...etc'; must not start or finish with space")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WarehouseCommonDto)) return false;
        WarehouseCommonDto that = (WarehouseCommonDto) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getWhAddress(), that.getWhAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getWhAddress());
    }

    @Override
    public String toString() {
        return "WarehouseCommonDto{" +
                "id=" + id +
                ", whAddress='" + whAddress + '\'' +
                '}';
    }
}
