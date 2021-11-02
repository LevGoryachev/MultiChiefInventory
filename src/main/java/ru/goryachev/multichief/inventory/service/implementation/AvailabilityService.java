package ru.goryachev.multichief.inventory.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.inventory.exception.EmptyListException;
import ru.goryachev.multichief.inventory.exception.ObjectNotFoundException;
import ru.goryachev.multichief.inventory.model.dto.projection.ItemProjection;
import ru.goryachev.multichief.inventory.model.dto.request.ItemRequestDto;
import ru.goryachev.multichief.inventory.model.entity.Availability;
import ru.goryachev.multichief.inventory.repository.*;
import ru.goryachev.multichief.inventory.service.SpecialService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * AvailabilityService gets ItemDto (id and quantity of existing material) and converts to Availability (entity) for saving in DB;
 * provides CRUD for Availability.
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
@PropertySource("classpath:service_layer.properties")
public class AvailabilityService implements SpecialService {

    private AvailabilityRepository availabilityRepository;
    private WarehouseRepository warehouseRepository;
    private MaterialRepository materialRepository; //look for the cached data (where?)
    @Value("${model.entity.alias.warehouse}")
    private String warehouseEntityAlias;
    @Value("${model.entity.alias.availability}")
    private String availabilityEntityAlias;

    @Autowired
    public AvailabilityService(AvailabilityRepository availabilityRepository, WarehouseRepository warehouseRepository, MaterialRepository materialRepository) {
        this.availabilityRepository = availabilityRepository;
        this.warehouseRepository = warehouseRepository;
        this.materialRepository = materialRepository;
    }

    public List<ItemProjection> getAllByWarehouseId(Long warehouseId) throws ObjectNotFoundException {

        if (!warehouseRepository.existsById(warehouseId)){
            throw new ObjectNotFoundException(warehouseEntityAlias, warehouseId);
        }

        List<ItemProjection> availabilityList = availabilityRepository.findByWarehouseId(warehouseId);

        if (availabilityList.isEmpty()) {
            throw new EmptyListException(availabilityEntityAlias);
        }
        return availabilityList;
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
