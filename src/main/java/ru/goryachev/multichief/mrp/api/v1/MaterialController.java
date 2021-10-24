package ru.goryachev.multichief.mrp.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.goryachev.multichief.mrp.exception.ObjectNotFoundException;
import ru.goryachev.multichief.mrp.model.entity.Material;
import ru.goryachev.multichief.mrp.service.implementation.MaterialService;

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
    public ResponseEntity<List<Material>> getAllMaterials () {
        return new ResponseEntity<>(materialService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Material> getById (@PathVariable Long id) throws ObjectNotFoundException {
            return new ResponseEntity<>(materialService.getById(id), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Material> create (@RequestBody Material material) {
        return new ResponseEntity<>(materialService.create(material), HttpStatus.CREATED);
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
