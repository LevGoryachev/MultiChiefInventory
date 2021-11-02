package ru.goryachev.multichief.inventory.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.inventory.exception.EmptyListException;
import ru.goryachev.multichief.inventory.model.dto.common.WarehouseCommonDto;
import ru.goryachev.multichief.inventory.model.entity.Warehouse;
import ru.goryachev.multichief.inventory.repository.WarehouseRepository;
import ru.goryachev.multichief.inventory.service.converter.WarehouseConverter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * WarehouseService works with Warehouse (entities and DTOs)
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
@PropertySource("classpath:service_layer.properties")
public class WarehouseService {

    private WarehouseRepository warehouseRepository;
    private WarehouseConverter warehouseConverter;

    @Value("${model.entity.alias.warehouse}")
    private String warehouseEntityAlias;

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository, WarehouseConverter warehouseConverter) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseConverter = warehouseConverter;
    }

    public List<WarehouseCommonDto> getAll () {
        List<Warehouse> allWarehouses = warehouseRepository.findAll();
        if (allWarehouses.isEmpty()) {
            throw new EmptyListException(warehouseEntityAlias);
        }
        return allWarehouses.stream().map(warehouseConverter::entityToDto).collect(Collectors.toList());
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
