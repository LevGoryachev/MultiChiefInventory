package ru.goryachev.multichief.mrp.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.mrp.exception.EmptyListException;
import ru.goryachev.multichief.mrp.model.entity.Warehouse;
import ru.goryachev.multichief.mrp.repository.WarehouseRepository;

import java.util.List;

/**
 * WarehouseService works with Warehouse (entities and DTOs)
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
public class WarehouseService {

    private WarehouseRepository warehouseRepository;
    private final String ENTITY_TYPE_NAME = "Warehouse";

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<Warehouse> getAll () {
        List<Warehouse> allWarehouses = warehouseRepository.findAll();
        if (allWarehouses.isEmpty()) {
            throw new EmptyListException(ENTITY_TYPE_NAME);
        }
        return allWarehouses;
    }

    public Warehouse create (Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public Warehouse update (Warehouse modifiedWarehouse) {
        return warehouseRepository.save(modifiedWarehouse);
    }

    public void delete (Long id) {
        warehouseRepository.deleteById(id);
    }

}
