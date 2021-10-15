package ru.goryachev.multichief.mrp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.mrp.model.dto.BomResponseDto;
import ru.goryachev.multichief.mrp.model.dto.ItemRequestDto;
import ru.goryachev.multichief.mrp.model.entity.BomItem;
import ru.goryachev.multichief.mrp.repository.BomItemRepository;
import ru.goryachev.multichief.mrp.repository.BomRepository;
import ru.goryachev.multichief.mrp.repository.MaterialRepository;

import javax.transaction.Transactional;

/**
 * BomItemService gets ItemDto (id and quantity of existing material) and converts to BomItem (entity) for saving in DB;
 * gets BomItem (data from DB) and converts to BomDto (Bill of Materials with items).
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
    public BomResponseDto getBomResponseDto (Long bomId) {
        BomResponseDto bomResponseDto = new BomResponseDto();
        bomResponseDto.setId(bomId);
        bomResponseDto.setInternalDocNum(bomRepository.findById(bomId).get().getInternalDocNum());
        bomResponseDto.setItems(bomItemRepository.findByBomId(bomId));
        return bomResponseDto;
    }

    public BomItem save (Long bomId, ItemRequestDto itemRequestDto) {
        BomItem bomItem = new BomItem(bomRepository.getOne(bomId), materialRepository.getOne(itemRequestDto.getMaterialId()));
        bomItem.setBomQty(itemRequestDto.getQty());
        return bomItemRepository.save(bomItem);
    }

    @Transactional
    public void delete (Long bom_id, Long material_id) {
       bomItemRepository.deleteByBomIdAndMaterialId(bom_id, material_id);
    }
}
