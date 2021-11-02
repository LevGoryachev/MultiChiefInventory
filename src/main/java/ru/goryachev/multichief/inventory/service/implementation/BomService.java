package ru.goryachev.multichief.inventory.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.inventory.exception.EmptyListException;
import ru.goryachev.multichief.inventory.model.dto.common.BomCommonDto;
import ru.goryachev.multichief.inventory.model.entity.Bom;
import ru.goryachev.multichief.inventory.repository.BomRepository;
import ru.goryachev.multichief.inventory.service.converter.BomConverter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * BomService works with Bom (Bill of material entities and DTOs)
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
@PropertySource("classpath:service_layer.properties")
public class BomService {

    private BomRepository bomRepository;
    private BomConverter bomConverter;

    @Value("${model.entity.alias.bom}")
    private String bomEntityAlias;

    @Autowired
    public BomService(BomRepository bomRepository, BomConverter bomConverter) {
        this.bomRepository = bomRepository;
        this.bomConverter = bomConverter;
    }

    public List<BomCommonDto> getAll () {
        List<Bom> allBoms = bomRepository.findAll();
        if (allBoms.isEmpty()) {
            throw new EmptyListException(bomEntityAlias);
        }
        return allBoms.stream().map(bomConverter::entityToDto).collect(Collectors.toList());
    }

    public Bom create (Bom bom) {
        return bomRepository.save(bom);
    }

    public Bom update (Bom modifiedBom) {
        return bomRepository.save(modifiedBom);
    }

    public void delete (Long id) {
        bomRepository.deleteById(id);
    }

}
