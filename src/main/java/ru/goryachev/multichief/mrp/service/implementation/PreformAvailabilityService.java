package ru.goryachev.multichief.mrp.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.mrp.model.dto.preform.PreformAvailabilityResponseDto;
import ru.goryachev.multichief.mrp.model.dto.preform.PreformBomResponseDto;
import ru.goryachev.multichief.mrp.repository.AvailabilityRepository;
import ru.goryachev.multichief.mrp.repository.BomItemRepository;
import ru.goryachev.multichief.mrp.repository.BomRepository;
import ru.goryachev.multichief.mrp.repository.WarehouseRepository;
import ru.goryachev.multichief.mrp.service.PreformService;

import javax.transaction.Transactional;

/**
 * PreformAvailabilityService gets Warehouse, Availability (entities) and converts to PreformAvailabilityResponseDto
 * (preform of list of materials with items in certain warehouse).
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
public class PreformAvailabilityService implements PreformService {

    private AvailabilityRepository availabilityRepository;
    private WarehouseRepository warehouseRepository;

    @Autowired
    public PreformAvailabilityService(AvailabilityRepository availabilityRepository,  WarehouseRepository warehouseRepository) {
        this.availabilityRepository = availabilityRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    @Transactional
    public PreformAvailabilityResponseDto getPreform (Long warehouseId) {
        PreformAvailabilityResponseDto preformAvailabilityResponseDto = new PreformAvailabilityResponseDto();
        preformAvailabilityResponseDto.setId(warehouseId);
        preformAvailabilityResponseDto.setWhAddress(warehouseRepository.findById(warehouseId).get().getWhAddress());
        preformAvailabilityResponseDto.setItems(availabilityRepository.findByWarehouseId(warehouseId));
        return preformAvailabilityResponseDto;
    }
}
