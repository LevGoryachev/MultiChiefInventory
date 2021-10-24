package ru.goryachev.multichief.mrp.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.mrp.model.dto.ItemRequestDto;
import ru.goryachev.multichief.mrp.model.entity.BomItem;
import ru.goryachev.multichief.mrp.repository.BomItemRepository;
import ru.goryachev.multichief.mrp.repository.BomRepository;
import ru.goryachev.multichief.mrp.repository.MaterialRepository;

import javax.transaction.Transactional;

/**
 * BomItemService gets ItemDto (id and quantity of existing material) and converts to BomItem (entity) for saving in DB;
 * gets BomItem (data from DB) and converts to PreformBomResponseDto (Bill of Materials with items).
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
public class BomItemService {

    private BomItemRepository bomItemRepository;
    private BomRepository bomRepository;
    private MaterialRepository materialRepository; //look for the cached data (where?)

    @Autowired
    public BomItemService(BomItemRepository bomItemRepository, BomRepository bomRepository, MaterialRepository materialRepository) {
        this.bomItemRepository = bomItemRepository;
        this.bomRepository = bomRepository;
        this.materialRepository = materialRepository;
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
