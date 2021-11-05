package ru.goryachev.multichief.inventory.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.inventory.exception.MultiChiefObjectNotFoundException;
import ru.goryachev.multichief.inventory.exception.MultiChiefEmptyListException;
import ru.goryachev.multichief.inventory.model.dto.common.MaterialCommonDto;
import ru.goryachev.multichief.inventory.model.entity.Material;
import ru.goryachev.multichief.inventory.repository.MaterialRepository;
import ru.goryachev.multichief.inventory.service.converter.MaterialConverter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * MaterialService works with types: MaterialCommonDto, Material
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
@PropertySource("classpath:service_layer.properties")
public class MaterialService {

    private MaterialRepository materialRepository;
    private MaterialConverter materialConverter;

    @Value("${model.entity.alias.material}")
    private String materialEntityAlias;

    @Autowired
    public MaterialService(MaterialRepository materialRepository, MaterialConverter materialConverter) {
        this.materialRepository = materialRepository;
        this.materialConverter = materialConverter;
    }

    public List<MaterialCommonDto> getAll () throws MultiChiefEmptyListException {
        List<Material> allMaterials = materialRepository.findAll();
        if (allMaterials.isEmpty()) {
            throw new MultiChiefEmptyListException(materialEntityAlias);
        }
        return allMaterials.stream().map(materialConverter::entityToDto).collect(Collectors.toList());
    }

    //findById: items.stream().findAny().map((e) -> items).orElseThrow(NotFoundException::new);

    public MaterialCommonDto getById (Long id) throws MultiChiefObjectNotFoundException {
        Material material = materialRepository.findById(id).orElseThrow(() -> new MultiChiefObjectNotFoundException(materialEntityAlias, id));
        return materialConverter.entityToDto(material);
    }

    public Map<String, Object> create (MaterialCommonDto materialCommonDto) {
        Material material = materialConverter.dtoToEntity(materialCommonDto);

        Material savedMaterial = materialRepository.save(material);
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("result", materialEntityAlias +" " + "was saved in DB");
        responseBody.put("id", savedMaterial.getId());
        responseBody.put("name", savedMaterial.getName());
        return responseBody;
    }

    public Material update (Material modifiedMaterial) {
        return materialRepository.save(modifiedMaterial);
    }

    public void delete (Long id) {
        materialRepository.deleteById(id);
    }

}
