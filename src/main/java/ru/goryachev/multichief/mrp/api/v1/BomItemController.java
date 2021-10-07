package ru.goryachev.multichief.mrp.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.goryachev.multichief.mrp.model.entity.BomItem;
import ru.goryachev.multichief.mrp.service.BomItemService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bomitem")
public class BomItemController {

    private BomItemService bomItemService;

    @Autowired
    public BomItemController(BomItemService bomItemService) {
        this.bomItemService = bomItemService;
    }

    @GetMapping
    public ResponseEntity<List<BomItem>> getAllMaterials () {
        return new ResponseEntity<>(bomItemService.getAll(), HttpStatus.OK);
    }

   /* @GetMapping("{id}")
    public ResponseEntity<BomOld> getById (@PathVariable Long id) {
        return new ResponseEntity<>(bomService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BomOld> create (@RequestBody BomOld bomOld) {
        return new ResponseEntity<>(bomService.create(bomOld), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<BomOld> update (@RequestBody BomOld bomOld) {
        return new ResponseEntity<>(bomService.update(bomOld), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Throwable> delete (@PathVariable Long id) {
        bomService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/
}
