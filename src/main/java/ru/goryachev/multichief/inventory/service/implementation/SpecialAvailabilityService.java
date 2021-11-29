package ru.goryachev.multichief.inventory.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.inventory.exception.MultiChiefEmptyListException;
import ru.goryachev.multichief.inventory.exception.MultiChiefObjectNotFoundException;
import ru.goryachev.multichief.inventory.model.dto.projection.ItemProjection;
import ru.goryachev.multichief.inventory.model.dto.request.ItemRequestDto;
import ru.goryachev.multichief.inventory.model.entity.*;
import ru.goryachev.multichief.inventory.repository.*;
import ru.goryachev.multichief.inventory.service.SpecialService;

import javax.transaction.Transactional;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * SpecialAvailabilityService gets ItemDto (id and quantity of existing material) and converts to Availability (entity) for saving in DB;
 * provides CRUD for Availability.
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
@PropertySource("classpath:service_layer.properties")
public class SpecialAvailabilityService implements SpecialService {

    private AvailabilityRepository availabilityRepository;
    private WarehouseRepository warehouseRepository;
    private MaterialRepository materialRepository; //look for the cached data (where?)
    @Value("${model.entity.alias.warehouse}")
    private String warehouseEntityAlias;
    @Value("${model.entity.alias.availability}")
    private String availabilityEntityAlias;
    @Value("${model.entity.alias.material}")
    private String materialEntityAlias;

    @Autowired
    public SpecialAvailabilityService(AvailabilityRepository availabilityRepository, WarehouseRepository warehouseRepository, MaterialRepository materialRepository) {
        this.availabilityRepository = availabilityRepository;
        this.warehouseRepository = warehouseRepository;
        this.materialRepository = materialRepository;
    }

    public List<ItemProjection> getAllByWarehouseId(Long warehouseId) {

        if (!warehouseRepository.existsById(warehouseId)){
            throw new MultiChiefObjectNotFoundException(warehouseEntityAlias, warehouseId);
        }

        List<ItemProjection> availabilityList = availabilityRepository.findByWarehouseId(warehouseId);

        if (availabilityList.isEmpty()) {
            throw new MultiChiefEmptyListException(availabilityEntityAlias);
        }
        return availabilityList;
    }

    @Transactional
    public Map<String, Object> save (Long warehouseId, ItemRequestDto itemRequestDto) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElseThrow(() -> new MultiChiefObjectNotFoundException(warehouseEntityAlias, warehouseId));
        Material material = materialRepository.findById(itemRequestDto.getMaterialId()).orElseThrow(() -> new MultiChiefObjectNotFoundException(materialEntityAlias, itemRequestDto.getMaterialId()));
        Availability availability = new Availability(warehouse, material, itemRequestDto.getQty());

        Availability savedAvailability = availabilityRepository.save(availability);

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("result", availabilityEntityAlias +" " + "was saved in DB");
        responseBody.put("material", savedAvailability.getMaterial().getName());
        responseBody.put("quantity", savedAvailability.getQty());
        return responseBody;


        /*Availability availability = new Availability(warehouseRepository.getOne(warehouseId), materialRepository.getOne(itemRequestDto.getMaterialId()));
        availability.setQty(itemRequestDto.getQty());
        return availabilityRepository.save(availability);*/
    }

    @Transactional
    public void delete (Long warehouseId, Long materialId) {
        availabilityRepository.deleteByWarehouseIdAndMaterialId(warehouseId, materialId);
    }
}
