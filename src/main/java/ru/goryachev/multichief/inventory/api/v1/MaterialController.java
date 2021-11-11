package ru.goryachev.multichief.inventory.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.goryachev.multichief.inventory.exception.MultiChiefEmptyListException;
import ru.goryachev.multichief.inventory.exception.MultiChiefObjectNotFoundException;
import ru.goryachev.multichief.inventory.model.dto.CommonDto;
import ru.goryachev.multichief.inventory.model.dto.common.MaterialCommonDto;
import ru.goryachev.multichief.inventory.service.implementation.StandardMaterialService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/materials")
public class MaterialController {

    private StandardMaterialService standardMaterialService;

    @Autowired
    public MaterialController(StandardMaterialService standardMaterialService) {
        this.standardMaterialService = standardMaterialService;
    }

    @GetMapping
    public ResponseEntity<List<CommonDto>> getAllMaterials () throws MultiChiefEmptyListException {
        return new ResponseEntity<>(standardMaterialService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommonDto> getById (@PathVariable Long id) throws MultiChiefObjectNotFoundException {
            return new ResponseEntity<>(standardMaterialService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create (@RequestBody @Valid MaterialCommonDto materialCommonDto) {
        return new ResponseEntity<>(standardMaterialService.create(materialCommonDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> update (@RequestBody @Valid MaterialCommonDto modifiedMaterial) {
        return new ResponseEntity<>(standardMaterialService.update(modifiedMaterial), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete (@PathVariable Long id) {
        return new ResponseEntity<>(standardMaterialService.delete(id),HttpStatus.OK);
    }
}
