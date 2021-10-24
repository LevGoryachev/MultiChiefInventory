package ru.goryachev.multichief.mrp.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.mrp.model.dto.ItemRequestDto;
import ru.goryachev.multichief.mrp.model.entity.Availability;
import ru.goryachev.multichief.mrp.repository.*;

import javax.transaction.Transactional;

/**
 * AvailabilityService gets ItemDto (id and quantity of existing material) and converts to Availability (entity) for saving in DB;
 * provides CRUD for Availability.
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
public class AvailabilityService {

    private AvailabilityRepository availabilityRepository;
    private WarehouseRepository warehouseRepository;
    private MaterialRepository materialRepository; //look for the cached data (where?)

    @Autowired
    public AvailabilityService(AvailabilityRepository availabilityRepository, WarehouseRepository warehouseRepository, MaterialRepository materialRepository) {
        this.availabilityRepository = availabilityRepository;
        this.warehouseRepository = warehouseRepository;
        this.materialRepository = materialRepository;
    }

    @Transactional
    public Availability save (Long warehouseId, ItemRequestDto itemRequestDto) {
        Availability availability = new Availability(warehouseRepository.getOne(warehouseId), materialRepository.getOne(itemRequestDto.getMaterialId()));
        availability.setQty(itemRequestDto.getQty());
        return availabilityRepository.save(availability);
    }

    @Transactional
    public void delete (Long warehouseId, Long materialId) {
        availabilityRepository.deleteByWarehouseIdAndMaterialId(warehouseId, materialId);
    }
}
