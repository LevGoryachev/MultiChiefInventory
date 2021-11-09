package ru.goryachev.multichief.inventory.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.inventory.exception.MultiChiefEmptyListException;
import ru.goryachev.multichief.inventory.model.dto.CommonDto;
import ru.goryachev.multichief.inventory.model.dto.common.WarehouseCommonDto;
import ru.goryachev.multichief.inventory.model.entity.Material;
import ru.goryachev.multichief.inventory.model.entity.Warehouse;
import ru.goryachev.multichief.inventory.repository.WarehouseRepository;
import ru.goryachev.multichief.inventory.service.StandardService;
import ru.goryachev.multichief.inventory.service.converter.WarehouseConverter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * WarehouseService works with Warehouse (entities and DTOs)
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
@PropertySource("classpath:service_layer.properties")
public class WarehouseService implements StandardService {

    private WarehouseRepository warehouseRepository;
    private WarehouseConverter warehouseConverter;

    @Value("${model.entity.alias.warehouse}")
    private String warehouseEntityAlias;

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository, WarehouseConverter warehouseConverter) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseConverter = warehouseConverter;
    }

    @Override
    public List<CommonDto> getAll () throws MultiChiefEmptyListException {
        List<Warehouse> allWarehouses = warehouseRepository.findAll();
        if (allWarehouses.isEmpty()) {
            throw new MultiChiefEmptyListException(warehouseEntityAlias);
        }
        return allWarehouses.stream().map(warehouseConverter::entityToDto).collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> create (CommonDto warehouseCommonDto) {
        Warehouse warehouse = (Warehouse) warehouseConverter.dtoToEntity(warehouseCommonDto);

        Warehouse savedWarehouse = warehouseRepository.save(warehouse);
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("result", warehouseEntityAlias + " " + "was saved in DB");
        responseBody.put("id", savedWarehouse.getId());
        responseBody.put("address", savedWarehouse.getWhAddress());
        return responseBody;
    }

    @Override
    public Map<String, Object> update (CommonDto modifiedWarehouse) {
        Warehouse warehouse = (Warehouse) warehouseConverter.dtoToEntity(modifiedWarehouse);

        Warehouse savedWarehouse = warehouseRepository.save(warehouse);
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("result", warehouseEntityAlias + " " + "was updated");
        responseBody.put("id", savedWarehouse.getId());
        responseBody.put("address", savedWarehouse.getWhAddress());
        return responseBody;
    }

    @Override
    public Map<String, Object> delete (Long id) {
        warehouseRepository.deleteById(id);
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("result", warehouseEntityAlias + " " + "with id" + " " + id + " " + "was deleted");
        return responseBody;
    }

}
