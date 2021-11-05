package ru.goryachev.multichief.inventory.model.dto.common;

import ru.goryachev.multichief.inventory.model.dto.CommonDto;

import javax.validation.constraints.*;


/**
 * MaterialCommonDto is an intermediate object of catalogue unit of all materials (for building construction),
 * and it is used for requests and responses (communication between microservices).
 * @author Lev Goryachev
 * @version 1.1
 */

public class MaterialCommonDto implements CommonDto {

    private Long id;

    @NotBlank
    @Size(min=2, max=80)
    @Pattern(regexp = "(\\S+\\s?\\S+)+", message = "must match: 'word/space/word/space/word...etc'; must not start or finish with space")
    private String name;

    @NotBlank
    @Size(min=1, max=25)
    private String um;

    @NotNull
    @Min(1)
    @Max(1000000)
    private Integer unitWeightKg;

    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public Integer getUnitWeightKg() {
        return unitWeightKg;
    }

    public void setUnitWeightKg(Integer unit_weight_kg) {
        this.unitWeightKg = unit_weight_kg;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
