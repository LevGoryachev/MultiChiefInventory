package ru.goryachev.multichief.mrp.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.mrp.model.dto.response.PreformBomResponseDto;
import ru.goryachev.multichief.mrp.repository.BomItemRepository;
import ru.goryachev.multichief.mrp.repository.BomRepository;
import ru.goryachev.multichief.mrp.service.PreformService;

import javax.transaction.Transactional;

/**
 * PreformBomService gets Bom, BomItem (entities) and converts to PreformBomResponseDto (preform of bill of materials with items).
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
public class PreformBomService implements PreformService {

    private BomItemRepository bomItemRepository;
    private BomRepository bomRepository;

    @Autowired
    public PreformBomService(BomItemRepository bomItemRepository, BomRepository bomRepository) {
        this.bomItemRepository = bomItemRepository;
        this.bomRepository = bomRepository;
    }

    @Override
    @Transactional
    public PreformBomResponseDto getPreform (Long bomId) {
        PreformBomResponseDto preformBomResponseDto = new PreformBomResponseDto();
        preformBomResponseDto.setId(bomId);
        preformBomResponseDto.setInternalDocNum(bomRepository.findById(bomId).get().getInternalDocNum());
        preformBomResponseDto.setItems(bomItemRepository.findByBomId(bomId));
        return preformBomResponseDto;
    }
}
