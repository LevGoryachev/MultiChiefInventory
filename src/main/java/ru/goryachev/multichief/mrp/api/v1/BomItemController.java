package ru.goryachev.multichief.mrp.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.goryachev.multichief.mrp.model.dto.BomResponseDto;
import ru.goryachev.multichief.mrp.model.dto.ItemRequestDto;
import ru.goryachev.multichief.mrp.model.entity.BomItem;
import ru.goryachev.multichief.mrp.service.BomItemService;

@RestController
@RequestMapping("/api/v1/bom/{bomId}/items")
public class BomItemController {

    private BomItemService bomItemService;

    @Autowired
    public BomItemController(BomItemService bomItemService) {
        this.bomItemService = bomItemService;
    }

    @GetMapping
    public ResponseEntity<BomResponseDto> getBomResponseDto (@PathVariable Long bomId) {
        return new ResponseEntity<>(bomItemService.getBomResponseDto(bomId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BomItem> createItems (@PathVariable Long bomId, @RequestBody ItemRequestDto itemRequestDto) {
        return new ResponseEntity<>(bomItemService.save(bomId, itemRequestDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<BomItem> updateItems (@PathVariable Long bomId, @RequestBody ItemRequestDto itemRequestDto) {
        return new ResponseEntity<>(bomItemService.save(bomId, itemRequestDto), HttpStatus.CREATED);
    }

    @DeleteMapping("{materialId}")
    public ResponseEntity<Throwable> deleteItems (@PathVariable Long bomId, @PathVariable Long materialId) {
        bomItemService.delete(bomId, materialId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
