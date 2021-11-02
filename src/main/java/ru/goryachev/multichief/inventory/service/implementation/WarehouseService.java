package ru.goryachev.multichief.inventory.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.inventory.exception.EmptyListException;
import ru.goryachev.multichief.inventory.model.entity.Warehouse;
import ru.goryachev.multichief.inventory.repository.WarehouseRepository;

import java.util.List;

/**
 * WarehouseService works with Warehouse (entities and DTOs)
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
@PropertySource("classpath:service_layer.properties")
public class WarehouseService {

    private WarehouseRepository warehouseRepository;
    @Value("${model.entity.alias.warehouse}")
    private String warehouseEntityAlias;

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<Warehouse> getAll () {
        List<Warehouse> allWarehouses = warehouseRepository.findAll();
        if (allWarehouses.isEmpty()) {
            throw new EmptyListException(warehouseEntityAlias);
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
