package ru.goryachev.multichief.mrp.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.goryachev.multichief.mrp.model.entity.Warehouse;
import ru.goryachev.multichief.mrp.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/warehouses")
public class PreformWarehouseController {

    private WarehouseService warehouseService;

    @Autowired
    public PreformWarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
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
}
