package ru.goryachev.multichief.mrp.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.goryachev.multichief.mrp.model.dto.BomResponseDto;
import ru.goryachev.multichief.mrp.model.dto.ItemRequestDto;
import ru.goryachev.multichief.mrp.model.entity.Bom;
import ru.goryachev.multichief.mrp.model.entity.BomItem;
import ru.goryachev.multichief.mrp.service.BomItemService;
import ru.goryachev.multichief.mrp.service.BomService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/boms")
public class PreformBomController {

    private BomService bomService;
    private BomItemService bomItemService;

    @Autowired
    public PreformBomController(BomService bomService, BomItemService bomItemService) {
        this.bomService = bomService;
        this.bomItemService = bomItemService;
    }

    @GetMapping
    public ResponseEntity<List<Bom>> getAll () {
        return new ResponseEntity<>(bomService.getAll(), HttpStatus.OK);
    }

   /* @GetMapping // add @Params and getAllById and may be unit with getAllItems
    public ResponseEntity<List<Bom>> getBoms () {
        return new ResponseEntity<>(bomService.getAll(), HttpStatus.OK);
    }*/

    @PostMapping
    public ResponseEntity<Bom> createBoms (@RequestBody Bom bom) {
        return new ResponseEntity<>(bomService.create(bom), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Bom> updateBoms (@RequestBody Bom modifiedBom) {
        return new ResponseEntity<>(bomService.update(modifiedBom), HttpStatus.OK);
    }

    @DeleteMapping("{id}")//remove id and implement deleteAllBy
    public ResponseEntity<Throwable> deleteBoms (@PathVariable Long id) {
        bomService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * getBomResponseDto(bill of material ID) returns BomResponseDto - a preform of bill of material.
     * The preform can be adapted by the consumer (next microservice) for preparing ready-to-use document (ViewModel).
     */
    @GetMapping("{bomId}")
    public ResponseEntity<BomResponseDto> getBomResponseDto (@PathVariable Long bomId) {
        return new ResponseEntity<>(bomItemService.getBomResponseDto(bomId), HttpStatus.OK);
    }

    @GetMapping("{bomId}/items")
    public ResponseEntity<BomResponseDto> getAllItems (@PathVariable Long bomId) {
        return new ResponseEntity<>(bomItemService.getBomResponseDto(bomId), HttpStatus.OK);
    }

    /*@GetMapping("{bomId}/items")// add @Params and getAllById and may be unit with getAllItems
    public ResponseEntity<BomResponseDto> getItems (@PathVariable Long bomId) {
        return new ResponseEntity<>(bomItemService.getBomResponseDto(bomId), HttpStatus.OK);
    }*/

    /*@PostMapping
    public ResponseEntity<BomItem> createItems (@PathVariable Long bomId, @RequestBody Set<ItemRequestDto> itemRequestDtos) {
        return new ResponseEntity<>(bomItemService.save(bomId, itemRequestDtos), HttpStatus.CREATED);
    }*/

    @PostMapping("{bomId}/items")
    public ResponseEntity<BomItem> createItems (@PathVariable Long bomId, @RequestBody ItemRequestDto itemRequestDto) {
        return new ResponseEntity<>(bomItemService.save(bomId, itemRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("{bomId}/items")
    public ResponseEntity<BomItem> updateItems (@PathVariable Long bomId, @RequestBody ItemRequestDto modifiedItemDto) {
        return new ResponseEntity<>(bomItemService.save(bomId, modifiedItemDto), HttpStatus.CREATED);
    }

    @DeleteMapping("{bomId}/items/{materialId}")//remove id and implement deleteAllBy
    public ResponseEntity<Throwable> deleteItems (@PathVariable Long bomId, @PathVariable Long materialId) {
        bomItemService.delete(bomId, materialId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
