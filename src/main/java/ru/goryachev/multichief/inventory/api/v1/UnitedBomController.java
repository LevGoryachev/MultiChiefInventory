package ru.goryachev.multichief.inventory.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.goryachev.multichief.inventory.model.dto.PreformDto;
import ru.goryachev.multichief.inventory.model.dto.common.BomCommonDto;
import ru.goryachev.multichief.inventory.model.dto.request.ItemRequestDto;
import ru.goryachev.multichief.inventory.model.dto.projection.ItemProjection;
import ru.goryachev.multichief.inventory.model.entity.Bom;
import ru.goryachev.multichief.inventory.model.entity.BomItem;
import ru.goryachev.multichief.inventory.service.implementation.SpecialBomItemService;
import ru.goryachev.multichief.inventory.service.implementation.BomService;
import ru.goryachev.multichief.inventory.service.implementation.PreformBomService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/boms")
public class UnitedBomController {

    private BomService bomService;
    private SpecialBomItemService specialBomItemService;
    private PreformBomService preformBomService;

    @Autowired
    public UnitedBomController(BomService bomService, SpecialBomItemService specialBomItemService, PreformBomService preformBomService) {
        this.bomService = bomService;
        this.specialBomItemService = specialBomItemService;
        this.preformBomService = preformBomService;
    }

    @GetMapping
    public ResponseEntity<List<BomCommonDto>> getAll () {
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

    @DeleteMapping("{bomId}")//remove id and implement deleteAllBy
    public ResponseEntity<Throwable> deleteBoms (@PathVariable Long bomId) {
        bomService.delete(bomId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * getBomPreform returns PreformBomResponseDto (implementation of PreformDto) - a preform of bill of materials document.
     * The preform can be used by the consumer (other microservice) for preparing ready-to-use document (ViewModel).
     */
    @GetMapping("{bomId}")
    public ResponseEntity<PreformDto> getPreformBom (@PathVariable Long bomId) throws Exception {
        return new ResponseEntity<>(preformBomService.getPreform(bomId), HttpStatus.OK);
    }

    @GetMapping("{bomId}/items")
    public ResponseEntity<List<ItemProjection>> getAllItems (@PathVariable Long bomId) throws Exception {
        return new ResponseEntity<>(specialBomItemService.getAllByBomId(bomId), HttpStatus.OK);
    }

    /*@GetMapping("{bomId}/items")// add @Params and getAllById and may be unit with getAllItems
    public ResponseEntity<PreformBomResponseDto> getItems (@PathVariable Long bomId) {
        return new ResponseEntity<>(bomItemService.getBomResponseDto(bomId), HttpStatus.OK);
    }*/

    /*@PostMapping
    public ResponseEntity<BomItem> createItems (@PathVariable Long bomId, @RequestBody Set<ItemRequestDto> itemRequestDtos) {
        return new ResponseEntity<>(bomItemService.save(bomId, itemRequestDtos), HttpStatus.CREATED);
    }*/

    @PostMapping("{bomId}/items")
    public ResponseEntity<BomItem> createItems (@PathVariable Long bomId, @RequestBody ItemRequestDto itemRequestDto) {
        return new ResponseEntity<>(specialBomItemService.save(bomId, itemRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("{bomId}/items")
    public ResponseEntity<BomItem> updateItems (@PathVariable Long bomId, @RequestBody ItemRequestDto modifiedItemDto) {
        return new ResponseEntity<>(specialBomItemService.save(bomId, modifiedItemDto), HttpStatus.CREATED);
    }

    @DeleteMapping("{bomId}/items/{materialId}")//remove id and implement deleteAllBy
    public ResponseEntity<Throwable> deleteItems (@PathVariable Long bomId, @PathVariable Long materialId) {
        specialBomItemService.delete(bomId, materialId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
