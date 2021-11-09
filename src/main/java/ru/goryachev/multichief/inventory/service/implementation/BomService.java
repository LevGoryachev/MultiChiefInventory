package ru.goryachev.multichief.inventory.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.inventory.exception.MultiChiefEmptyListException;
import ru.goryachev.multichief.inventory.model.dto.CommonDto;
import ru.goryachev.multichief.inventory.model.entity.Bom;
import ru.goryachev.multichief.inventory.repository.BomRepository;
import ru.goryachev.multichief.inventory.service.StandardService;
import ru.goryachev.multichief.inventory.service.converter.BomConverter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * BomService works with Bom (Bill of material entities and DTOs)
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
@PropertySource("classpath:service_layer.properties")
public class BomService implements StandardService {

    private BomRepository bomRepository;
    private BomConverter bomConverter;

    @Value("${model.entity.alias.bom}")
    private String bomEntityAlias;

    @Autowired
    public BomService(BomRepository bomRepository, BomConverter bomConverter) {
        this.bomRepository = bomRepository;
        this.bomConverter = bomConverter;
    }

    @Override
    public List<CommonDto> getAll () throws MultiChiefEmptyListException {
        List<Bom> allBoms = bomRepository.findAll();
        if (allBoms.isEmpty()) {
            throw new MultiChiefEmptyListException(bomEntityAlias);
        }
        return allBoms.stream().map(bomConverter::entityToDto).collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> create (CommonDto bomCommonDto) {
        Bom bom = (Bom) bomConverter.dtoToEntity(bomCommonDto);

        Bom savedBom = bomRepository.save(bom);
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("result", bomEntityAlias + " " + "was saved in DB");
        responseBody.put("id", savedBom.getId());
        responseBody.put("internal document number", savedBom.getInternalDocNum());
        return responseBody;
    }

    @Override
    public Map<String, Object> update (CommonDto modifiedBom) {
        Bom bom = (Bom) bomConverter.dtoToEntity(modifiedBom);

        Bom savedBom = bomRepository.save(bom);
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("result", bomEntityAlias + " " + "was updated");
        responseBody.put("id", savedBom.getId());
        responseBody.put("internal document number", savedBom.getInternalDocNum());
        return responseBody;

    }

    @Override
    public Map<String, Object> delete (Long id) {
        bomRepository.deleteById(id);
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("result", bomEntityAlias + " " + "with id" + " " + id + " " + "was deleted");
        return responseBody;
    }

}
