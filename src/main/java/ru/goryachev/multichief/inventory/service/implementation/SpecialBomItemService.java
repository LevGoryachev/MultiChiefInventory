package ru.goryachev.multichief.inventory.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.inventory.exception.MultiChiefEmptyListException;
import ru.goryachev.multichief.inventory.exception.MultiChiefObjectNotFoundException;
import ru.goryachev.multichief.inventory.model.dto.request.ItemRequestDto;
import ru.goryachev.multichief.inventory.model.dto.projection.ItemProjection;
import ru.goryachev.multichief.inventory.model.entity.Bom;
import ru.goryachev.multichief.inventory.model.entity.BomItem;
import ru.goryachev.multichief.inventory.model.entity.Material;
import ru.goryachev.multichief.inventory.repository.BomItemRepository;
import ru.goryachev.multichief.inventory.repository.BomRepository;
import ru.goryachev.multichief.inventory.repository.MaterialRepository;
import ru.goryachev.multichief.inventory.service.SpecialService;

import javax.transaction.Transactional;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * SpecialBomItemService gets ItemDto (id and quantity of existing material) and converts to BomItem (entity) for saving in DB;
 * provides CRUD for BomItem.
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
@PropertySource("classpath:service_layer.properties")
public class SpecialBomItemService implements SpecialService {

    private BomItemRepository bomItemRepository;
    private BomRepository bomRepository;
    private MaterialRepository materialRepository; //look for the cached data (where?)
    @Value("${model.entity.alias.bom}")
    private String bomEntityAlias;
    @Value("${model.entity.alias.bomitem}")
    private String bomitemEntityAlias;
    @Value("${model.entity.alias.material}")
    private String materialEntityAlias;

    @Autowired
    public SpecialBomItemService(BomItemRepository bomItemRepository, BomRepository bomRepository, MaterialRepository materialRepository) {
        this.bomItemRepository = bomItemRepository;
        this.bomRepository = bomRepository;
        this.materialRepository = materialRepository;
    }

    public List<ItemProjection> getAllByBomId(Long bomId) {

        if (!bomRepository.existsById(bomId)){
            throw new MultiChiefObjectNotFoundException(bomEntityAlias, bomId);
        }

        List<ItemProjection> bomItemList = bomItemRepository.findByBomId(bomId);

        if (bomItemList.isEmpty()) {
            throw new MultiChiefEmptyListException(bomitemEntityAlias);
        }
        return bomItemList;
    }

    @Transactional
    public Map<String, Object> save (Long bomId, ItemRequestDto itemRequestDto) {
        Bom bom = bomRepository.findById(bomId).orElseThrow(() -> new MultiChiefObjectNotFoundException(bomEntityAlias, bomId));
        Material material = materialRepository.findById(itemRequestDto.getMaterialId()).orElseThrow(() -> new MultiChiefObjectNotFoundException(materialEntityAlias, itemRequestDto.getMaterialId()));
        BomItem bomItem = new BomItem(bom, material, itemRequestDto.getQty());

        BomItem savedBomItem = bomItemRepository.save(bomItem);

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("result", bomitemEntityAlias +" " + "was saved in DB");
        responseBody.put("material", savedBomItem.getMaterial().getName());
        responseBody.put("quantity", savedBomItem.getQty());
        return responseBody;
    }

    @Transactional
    public void delete (Long bomId, Long materialId) {
       bomItemRepository.deleteByBomIdAndMaterialId(bomId, materialId);
    }
}
