package ru.goryachev.multichief.mrp.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.goryachev.multichief.mrp.model.entity.Bom;
import ru.goryachev.multichief.mrp.service.BomService;


import java.util.List;

@RestController
@RequestMapping("/api/v1/bom")
public class BomController {

    private BomService bomService;

    @Autowired
    public BomController(BomService bomService) {
        this.bomService = bomService;
    }

    @GetMapping
    public ResponseEntity<List<Bom>> getAllBills () {
        return new ResponseEntity<>(bomService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Bom> getById (@PathVariable Long id) {
        return new ResponseEntity<>(bomService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Bom> create (@RequestBody Bom bom) {
        return new ResponseEntity<>(bomService.create(bom), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Bom> update (@RequestBody Bom bom) {
        return new ResponseEntity<>(bomService.update(bom), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Throwable> delete (@PathVariable Long id) {
        bomService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
