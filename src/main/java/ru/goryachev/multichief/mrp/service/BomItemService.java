package ru.goryachev.multichief.mrp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.mrp.model.dto.ItemDto;
import ru.goryachev.multichief.mrp.model.entity.BomItem;
import ru.goryachev.multichief.mrp.repository.BomItemRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * BomItemService gets ItemDto (id and quantity of existing material) and converts to BomItem (entity) for saving in DB;
 * gets BomItem (data from DB) and converts to BomDto (Bill of Materials with items).
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
public class BomItemService {

    private BomItemRepository bomItemRepository;

    @Autowired
    public BomItemService(BomItemRepository bomItemRepository) {
        this.bomItemRepository = bomItemRepository;
    }

    public List<BomItem> getAll () {
        return bomItemRepository.findAll();
    }

    public BomItem save (Long BomId, ItemDto itemDto) {
        BomItem bomItem = new BomItem();
        bomItem.setBomId(BomId);
        bomItem.setMaterialId(itemDto.getMaterialId());
        bomItem.setBomQty(itemDto.getBomQty());
        return bomItemRepository.save(bomItem);
    }

    @Transactional
    public void delete (Long bom_id, Long material_id) {
       bomItemRepository.deleteByBomIdAndMaterialId(bom_id, material_id);
    }
}
