package ru.goryachev.multichief.mrp.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.goryachev.multichief.mrp.model.dto.ItemDto;
import ru.goryachev.multichief.mrp.model.entity.BomItem;
import ru.goryachev.multichief.mrp.service.BomItemService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bom/{bomId}/items")
public class BomItemController {

    private BomItemService bomItemService;

    @Autowired
    public BomItemController(BomItemService bomItemService) {
        this.bomItemService = bomItemService;
    }

    @GetMapping
    public ResponseEntity<List<BomItem>> getBomItems () {
        return new ResponseEntity<>(bomItemService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BomItem> create (@PathVariable Long bomId, @RequestBody ItemDto itemDto) {
        return new ResponseEntity<>(bomItemService.save(bomId, itemDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<BomItem> update (@PathVariable Long bomId, @RequestBody ItemDto itemDto) {
        return new ResponseEntity<>(bomItemService.save(bomId, itemDto), HttpStatus.CREATED);
    }

    @DeleteMapping("{materialId}")
    public ResponseEntity<Throwable> delete (@PathVariable Long bomId, @PathVariable Long materialId) {
        bomItemService.delete(bomId, materialId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
