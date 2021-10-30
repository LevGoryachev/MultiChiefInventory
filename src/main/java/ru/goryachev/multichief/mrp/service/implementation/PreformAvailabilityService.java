package ru.goryachev.multichief.mrp.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.mrp.exception.ObjectNotFoundException;
import ru.goryachev.multichief.mrp.model.dto.response.PreformWarehouseResponseDto;
import ru.goryachev.multichief.mrp.model.entity.Warehouse;
import ru.goryachev.multichief.mrp.repository.AvailabilityRepository;
import ru.goryachev.multichief.mrp.repository.WarehouseRepository;
import ru.goryachev.multichief.mrp.service.PreformService;

import javax.transaction.Transactional;

/**
 * PreformAvailabilityService gets Warehouse, Availability (entities) and converts to PreformWarehouseResponseDto
 * (preform of list of materials with items in certain warehouse).
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
@PropertySource("classpath:service_layer.properties")
public class PreformAvailabilityService implements PreformService {

    private AvailabilityRepository availabilityRepository;
    private WarehouseRepository warehouseRepository;
    @Value("${model.entity.alias.warehouse}")
    private String warehouseEntityAlias;

    @Autowired
    public PreformAvailabilityService(AvailabilityRepository availabilityRepository,  WarehouseRepository warehouseRepository) {
        this.availabilityRepository = availabilityRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    @Transactional
    public PreformWarehouseResponseDto getPreform (Long warehouseId) throws ObjectNotFoundException {

        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElseThrow(() -> new ObjectNotFoundException(warehouseEntityAlias, warehouseId));

        PreformWarehouseResponseDto preformWarehouseResponseDto = new PreformWarehouseResponseDto();
        preformWarehouseResponseDto.setId(warehouseId);
        preformWarehouseResponseDto.setWhAddress(warehouse.getWhAddress());
        preformWarehouseResponseDto.setItems(availabilityRepository.findByWarehouseId(warehouseId));
        return preformWarehouseResponseDto;
    }
}
