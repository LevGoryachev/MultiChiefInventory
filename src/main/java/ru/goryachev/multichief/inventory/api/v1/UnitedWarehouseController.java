package ru.goryachev.multichief.inventory.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.goryachev.multichief.inventory.model.dto.CommonDto;
import ru.goryachev.multichief.inventory.model.dto.common.WarehouseCommonDto;
import ru.goryachev.multichief.inventory.model.dto.projection.ItemProjection;
import ru.goryachev.multichief.inventory.model.dto.request.ItemRequestDto;
import ru.goryachev.multichief.inventory.model.dto.PreformDto;
import ru.goryachev.multichief.inventory.service.implementation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * MultiChiefInventory API: see app/swagger-ui/
 * @author Lev Goryachev
 * @version 1
 */

@RestController
@RequestMapping("/api/v1/warehouses")
public class UnitedWarehouseController {

    private StandardWarehouseService standardWarehouseService;
    private SpecialAvailabilityService specialAvailabilityService;
    private PreformAvailabilityService preformAvailabilityService;

    @Autowired
    public UnitedWarehouseController(StandardWarehouseService standardWarehouseService, SpecialAvailabilityService specialAvailabilityService, PreformAvailabilityService preformAvailabilityService) {
        this.standardWarehouseService = standardWarehouseService;
        this.specialAvailabilityService = specialAvailabilityService;
        this.preformAvailabilityService = preformAvailabilityService;
    }

    @GetMapping
    public ResponseEntity<List<CommonDto>> getAll () {
        return new ResponseEntity<>(standardWarehouseService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create (@RequestBody @Valid WarehouseCommonDto warehouseCommonDto) {
        return new ResponseEntity<>(standardWarehouseService.create(warehouseCommonDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> update (@RequestBody @Valid WarehouseCommonDto modifiedWarehouse) {
        return new ResponseEntity<>(standardWarehouseService.update(modifiedWarehouse), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete (@PathVariable Long id) {
        return new ResponseEntity<>(standardWarehouseService.delete(id), HttpStatus.OK);
    }

    /**
     * getBomPreform returns PreformWarehouseResponseDto (implementation of PreformDto) - a preform of list of materials in certain warehouse.
     * The preform can be used by the consumer (other microservice) for preparing ready-to-use document (ViewModel).
     */
    @GetMapping("{warehouseId}")
    public ResponseEntity<PreformDto> getPreformBom (@PathVariable Long warehouseId) {
        return new ResponseEntity<>(preformAvailabilityService.getPreform(warehouseId), HttpStatus.OK);
    }

    @GetMapping("{warehouseId}/items")
    public ResponseEntity<List<ItemProjection>> getAllItems (@PathVariable Long warehouseId) {
        return new ResponseEntity<>(specialAvailabilityService.getAllByWarehouseId(warehouseId), HttpStatus.OK);
    }

    /*@GetMapping("{bomId}/items")// add @Params and getAllById and may be unit with getAllItems
    public ResponseEntity<PreformBomResponseDto> getItems (@PathVariable Long bomId) {
        return new ResponseEntity<>(bomItemService.getBomResponseDto(bomId), HttpStatus.OK);
    }*/

    /*@PostMapping
    public ResponseEntity<BomItem> createItems (@PathVariable Long bomId, @RequestBody Set<ItemRequestDto> itemRequestDtos) {
        return new ResponseEntity<>(bomItemService.save(bomId, itemRequestDtos), HttpStatus.CREATED);
    }*/

    @PostMapping("{warehouseId}/items")
    public ResponseEntity<Object> createItems (@PathVariable Long warehouseId, @RequestBody ItemRequestDto itemRequestDto) {
        return new ResponseEntity<>(specialAvailabilityService.save(warehouseId, itemRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("{warehouseId}/items")
    public ResponseEntity<Object> updateItems (@PathVariable Long warehouseId, @RequestBody ItemRequestDto modifiedItemDto) {
        return new ResponseEntity<>(specialAvailabilityService.save(warehouseId, modifiedItemDto), HttpStatus.CREATED);
    }

    @DeleteMapping("{warehouseId}/items/{materialId}")//remove id and implement deleteAllBy
    public ResponseEntity<Throwable> deleteItems (@PathVariable Long warehouseId, @PathVariable Long materialId) {
        specialAvailabilityService.delete(warehouseId, materialId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
