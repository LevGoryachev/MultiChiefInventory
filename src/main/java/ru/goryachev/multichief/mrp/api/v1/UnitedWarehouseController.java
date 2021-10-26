package ru.goryachev.multichief.mrp.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.goryachev.multichief.mrp.exception.ObjectNotFoundException;
import ru.goryachev.multichief.mrp.model.dto.projection.ItemProjection;
import ru.goryachev.multichief.mrp.model.dto.request.ItemRequestDto;
import ru.goryachev.multichief.mrp.model.dto.PreformDto;
import ru.goryachev.multichief.mrp.model.entity.Availability;
import ru.goryachev.multichief.mrp.model.entity.Warehouse;
import ru.goryachev.multichief.mrp.service.implementation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/warehouses")
public class UnitedWarehouseController {

    private WarehouseService warehouseService;
    private AvailabilityService availabilityService;
    private PreformAvailabilityService preformAvailabilityService;

    @Autowired
    public UnitedWarehouseController(WarehouseService warehouseService, AvailabilityService availabilityService, PreformAvailabilityService preformAvailabilityService) {
        this.warehouseService = warehouseService;
        this.availabilityService = availabilityService;
        this.preformAvailabilityService = preformAvailabilityService;
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAll () {
        return new ResponseEntity<>(warehouseService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Warehouse> create (@RequestBody Warehouse warehouse) {
        return new ResponseEntity<>(warehouseService.create(warehouse), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Warehouse> update (@RequestBody Warehouse modifiedWarehouse) {
        return new ResponseEntity<>(warehouseService.update(modifiedWarehouse), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Throwable> delete (@PathVariable Long id) {
        warehouseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * getBomPreform returns PreformAvailabilityResponseDto (implementation of PreformDto) - a preform of list of materials in certain warehouse.
     * The preform can be used by the consumer (other microservice) for preparing ready-to-use document (ViewModel).
     */
    @GetMapping("{warehouseId}")
    public ResponseEntity<PreformDto> getPreformBom (@PathVariable Long warehouseId) {
        return new ResponseEntity<>(preformAvailabilityService.getPreform(warehouseId), HttpStatus.OK);
    }

    @GetMapping("{warehouseId}/items")
    public ResponseEntity<List<ItemProjection>> getAllItems (@PathVariable Long warehouseId) throws Exception {
        return new ResponseEntity<>(availabilityService.getAllByWarehouseId(warehouseId), HttpStatus.OK);
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
    public ResponseEntity<Availability> createItems (@PathVariable Long warehouseId, @RequestBody ItemRequestDto itemRequestDto) {
        return new ResponseEntity<>(availabilityService.save(warehouseId, itemRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("{warehouseId}/items")
    public ResponseEntity<Availability> updateItems (@PathVariable Long warehouseId, @RequestBody ItemRequestDto modifiedItemDto) {
        return new ResponseEntity<>(availabilityService.save(warehouseId, modifiedItemDto), HttpStatus.CREATED);
    }

    @DeleteMapping("{warehouseId}/items/{materialId}")//remove id and implement deleteAllBy
    public ResponseEntity<Throwable> deleteItems (@PathVariable Long warehouseId, @PathVariable Long materialId) {
        availabilityService.delete(warehouseId, materialId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
