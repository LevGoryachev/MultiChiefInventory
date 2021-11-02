package ru.goryachev.multichief.inventory.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.inventory.exception.ObjectNotFoundException;
import ru.goryachev.multichief.inventory.model.dto.response.PreformBomResponseDto;
import ru.goryachev.multichief.inventory.model.entity.Bom;
import ru.goryachev.multichief.inventory.repository.BomItemRepository;
import ru.goryachev.multichief.inventory.repository.BomRepository;
import ru.goryachev.multichief.inventory.service.PreformService;

import javax.transaction.Transactional;

/**
 * PreformBomService gets Bom, BomItem (entities) and converts to PreformBomResponseDto (preform of bill of materials with items).
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
@PropertySource("classpath:service_layer.properties")
public class PreformBomService implements PreformService {

    private BomItemRepository bomItemRepository;
    private BomRepository bomRepository;
    @Value("${model.entity.alias.bom}")
    private String bomEntityAlias;

    @Autowired
    public PreformBomService(BomItemRepository bomItemRepository, BomRepository bomRepository) {
        this.bomItemRepository = bomItemRepository;
        this.bomRepository = bomRepository;
    }

    @Override
    @Transactional
    public PreformBomResponseDto getPreform (Long bomId) throws ObjectNotFoundException {

        Bom bom = bomRepository.findById(bomId).orElseThrow(() -> new ObjectNotFoundException(bomEntityAlias, bomId));

        PreformBomResponseDto preformBomResponseDto = new PreformBomResponseDto();
        preformBomResponseDto.setId(bomId);
        preformBomResponseDto.setInternalDocNum(bom.getInternalDocNum());
        preformBomResponseDto.setItems(bomItemRepository.findByBomId(bomId));
        return preformBomResponseDto;
    }
}
