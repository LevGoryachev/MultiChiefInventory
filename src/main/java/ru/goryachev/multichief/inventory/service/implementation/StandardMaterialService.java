package ru.goryachev.multichief.inventory.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.inventory.exception.MultiChiefObjectNotFoundException;
import ru.goryachev.multichief.inventory.exception.MultiChiefEmptyListException;
import ru.goryachev.multichief.inventory.model.dto.CommonDto;
import ru.goryachev.multichief.inventory.model.entity.Material;
import ru.goryachev.multichief.inventory.repository.MaterialRepository;
import ru.goryachev.multichief.inventory.service.StandardService;
import ru.goryachev.multichief.inventory.service.converter.MaterialConverter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * StandardMaterialService works with types: MaterialCommonDto, Material
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
@PropertySource("classpath:service_layer.properties")
public class StandardMaterialService implements StandardService {

    private MaterialRepository materialRepository;
    private MaterialConverter materialConverter;

    @Value("${model.entity.alias.material}")
    private String materialEntityAlias;

    @Autowired
    public StandardMaterialService(MaterialRepository materialRepository, MaterialConverter materialConverter) {
        this.materialRepository = materialRepository;
        this.materialConverter = materialConverter;
    }

    @Override
    public List<CommonDto> getAll () {
        List<Material> allMaterials = materialRepository.findAll();
        if (allMaterials.isEmpty()) {
            throw new MultiChiefEmptyListException(materialEntityAlias);
        }
        return allMaterials.stream().map(materialConverter::entityToDto).collect(Collectors.toList());
    }

    //findById: items.stream().findAny().map((e) -> items).orElseThrow(NotFoundException::new);

    public CommonDto getById (Long id) {
        Material material = materialRepository.findById(id).orElseThrow(() -> new MultiChiefObjectNotFoundException(materialEntityAlias, id));
        return materialConverter.entityToDto(material);
    }

    @Override
    public Map<String, Object> create (CommonDto materialCommonDto) {
        Material material = (Material) materialConverter.dtoToEntity(materialCommonDto);

        Material savedMaterial = materialRepository.save(material);
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("result", materialEntityAlias + " " + "was saved in DB");
        responseBody.put("id", savedMaterial.getId());
        responseBody.put("name", savedMaterial.getName());
        return responseBody;
    }

    @Override
    public Map<String, Object> update (CommonDto modifiedMaterial) {
        Material material = (Material) materialConverter.dtoToEntity(modifiedMaterial);

        Material savedMaterial = materialRepository.save(material);
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("result", materialEntityAlias + " " + "was updated");
        responseBody.put("id", savedMaterial.getId());
        responseBody.put("name", savedMaterial.getName());
        return responseBody;
    }

    @Override
    public Map<String, Object> delete (Long id) {
        materialRepository.deleteById(id);
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("result", materialEntityAlias + " " + "with id" + " " + id + " " + "was deleted");
        return responseBody;
    }
}
