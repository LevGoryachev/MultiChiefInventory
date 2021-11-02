package ru.goryachev.multichief.inventory.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.inventory.exception.EmptyListException;
import ru.goryachev.multichief.inventory.exception.ObjectNotFoundException;
import ru.goryachev.multichief.inventory.model.dto.request.ItemRequestDto;
import ru.goryachev.multichief.inventory.model.dto.projection.ItemProjection;
import ru.goryachev.multichief.inventory.model.entity.BomItem;
import ru.goryachev.multichief.inventory.repository.BomItemRepository;
import ru.goryachev.multichief.inventory.repository.BomRepository;
import ru.goryachev.multichief.inventory.repository.MaterialRepository;
import ru.goryachev.multichief.inventory.service.SpecialService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * BomItemService gets ItemDto (id and quantity of existing material) and converts to BomItem (entity) for saving in DB;
 * provides CRUD for BomItem.
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
@PropertySource("classpath:service_layer.properties")
public class BomItemService implements SpecialService {

    private BomItemRepository bomItemRepository;
    private BomRepository bomRepository;
    private MaterialRepository materialRepository; //look for the cached data (where?)
    @Value("${model.entity.alias.bom}")
    private String bomEntityAlias;
    @Value("${model.entity.alias.bomitem}")
    private String bomitemEntityAlias;

    @Autowired
    public BomItemService(BomItemRepository bomItemRepository, BomRepository bomRepository, MaterialRepository materialRepository) {
        this.bomItemRepository = bomItemRepository;
        this.bomRepository = bomRepository;
        this.materialRepository = materialRepository;
    }

    public List<ItemProjection> getAllByBomId(Long bomId) throws ObjectNotFoundException {

        if (!bomRepository.existsById(bomId)){
            throw new ObjectNotFoundException(bomEntityAlias, bomId);
        }

        List<ItemProjection> bomItemList = bomItemRepository.findByBomId(bomId);

        if (bomItemList.isEmpty()) {
            throw new EmptyListException(bomitemEntityAlias);
        }
        return bomItemList;
    }

    @Transactional
    public BomItem save (Long bomId, ItemRequestDto itemRequestDto) {
        BomItem bomItem = new BomItem(bomRepository.getOne(bomId), materialRepository.getOne(itemRequestDto.getMaterialId()));
        bomItem.setQty(itemRequestDto.getQty());
        return bomItemRepository.save(bomItem);
    }

    @Transactional
    public void delete (Long bomId, Long materialId) {
       bomItemRepository.deleteByBomIdAndMaterialId(bomId, materialId);
    }
}
