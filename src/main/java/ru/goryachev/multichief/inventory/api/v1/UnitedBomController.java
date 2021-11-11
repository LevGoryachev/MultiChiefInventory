package ru.goryachev.multichief.inventory.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.goryachev.multichief.inventory.exception.MultiChiefEmptyListException;
import ru.goryachev.multichief.inventory.exception.MultiChiefObjectNotFoundException;
import ru.goryachev.multichief.inventory.model.dto.CommonDto;
import ru.goryachev.multichief.inventory.model.dto.PreformDto;
import ru.goryachev.multichief.inventory.model.dto.common.BomCommonDto;
import ru.goryachev.multichief.inventory.model.dto.request.ItemRequestDto;
import ru.goryachev.multichief.inventory.model.dto.projection.ItemProjection;
import ru.goryachev.multichief.inventory.service.implementation.SpecialBomItemService;
import ru.goryachev.multichief.inventory.service.implementation.StandardBomService;
import ru.goryachev.multichief.inventory.service.implementation.PreformBomService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/boms")
public class UnitedBomController {

    private StandardBomService standardBomService;
    private SpecialBomItemService specialBomItemService;
    private PreformBomService preformBomService;

    @Autowired
    public UnitedBomController(StandardBomService standardBomService, SpecialBomItemService specialBomItemService, PreformBomService preformBomService) {
        this.standardBomService = standardBomService;
        this.specialBomItemService = specialBomItemService;
        this.preformBomService = preformBomService;
    }

    @GetMapping
    public ResponseEntity<List<CommonDto>> getAll () throws MultiChiefEmptyListException {
        return new ResponseEntity<>(standardBomService.getAll(), HttpStatus.OK);
    }

   /* @GetMapping // add @Params and getAllById and may be unit with getAllItems
    public ResponseEntity<List<Bom>> getBoms () {
        return new ResponseEntity<>(bomService.getAll(), HttpStatus.OK);
    }*/

    @PostMapping
    public ResponseEntity<Object> createBoms (@RequestBody @Valid BomCommonDto bomCommonDto) {
        return new ResponseEntity<>(standardBomService.create(bomCommonDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateBoms (@RequestBody @Valid BomCommonDto modifiedBom) {
        return new ResponseEntity<>(standardBomService.update(modifiedBom), HttpStatus.OK);
    }

    @DeleteMapping("{bomId}")//remove id and implement deleteAllBy
    public ResponseEntity<Object> deleteBoms (@PathVariable Long bomId) {
        return new ResponseEntity<>(standardBomService.delete(bomId), HttpStatus.OK);
    }

    /**
     * getBomPreform returns PreformBomResponseDto (implementation of PreformDto) - a preform of bill of materials document.
     * The preform can be used by the consumer (other microservice) for preparing ready-to-use document (ViewModel).
     */
    @GetMapping("{bomId}")
    public ResponseEntity<PreformDto> getPreformBom (@PathVariable Long bomId) throws MultiChiefObjectNotFoundException {
        return new ResponseEntity<>(preformBomService.getPreform(bomId), HttpStatus.OK);
    }

    @GetMapping("{bomId}/items")
    public ResponseEntity<List<ItemProjection>> getAllItems (@PathVariable Long bomId) throws MultiChiefEmptyListException, MultiChiefObjectNotFoundException {
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
    public ResponseEntity<Object> createItems (@PathVariable Long bomId, @RequestBody @Valid ItemRequestDto itemRequestDto) throws MultiChiefObjectNotFoundException {
        return new ResponseEntity<>(specialBomItemService.save(bomId, itemRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("{bomId}/items")
    public ResponseEntity<Object> updateItems (@PathVariable Long bomId, @RequestBody @Valid ItemRequestDto modifiedItemDto) throws MultiChiefObjectNotFoundException {
        return new ResponseEntity<>(specialBomItemService.save(bomId, modifiedItemDto), HttpStatus.CREATED);
    }

    @DeleteMapping("{bomId}/items/{materialId}")//remove id and implement deleteAllBy
    public ResponseEntity<Throwable> deleteItems (@PathVariable Long bomId, @PathVariable Long materialId) {
        specialBomItemService.delete(bomId, materialId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
