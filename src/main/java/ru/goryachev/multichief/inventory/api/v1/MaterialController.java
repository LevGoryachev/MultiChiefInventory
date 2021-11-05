package ru.goryachev.multichief.inventory.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.goryachev.multichief.inventory.exception.MultiChiefEmptyListException;
import ru.goryachev.multichief.inventory.exception.MultiChiefObjectNotFoundException;
import ru.goryachev.multichief.inventory.model.dto.common.MaterialCommonDto;
import ru.goryachev.multichief.inventory.model.entity.Material;
import ru.goryachev.multichief.inventory.service.implementation.MaterialService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/materials")
public class MaterialController {

    private MaterialService materialService;

    @Autowired
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public ResponseEntity<List<MaterialCommonDto>> getAllMaterials () throws MultiChiefEmptyListException {
        return new ResponseEntity<>(materialService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<MaterialCommonDto> getById (@PathVariable Long id) throws MultiChiefObjectNotFoundException {
            return new ResponseEntity<>(materialService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create (@RequestBody @Valid MaterialCommonDto materialCommonDto) {
        return new ResponseEntity<>(materialService.create(materialCommonDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Material> update (@RequestBody Material modifiedMaterial) {
        return new ResponseEntity<>(materialService.update(modifiedMaterial), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Throwable> delete (@PathVariable Long id) {
        materialService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
