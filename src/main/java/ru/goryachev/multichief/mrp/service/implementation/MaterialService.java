package ru.goryachev.multichief.mrp.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.mrp.exception.ObjectNotFoundException;
import ru.goryachev.multichief.mrp.exception.EmptyListException;
import ru.goryachev.multichief.mrp.model.entity.Material;
import ru.goryachev.multichief.mrp.repository.MaterialRepository;

import java.util.List;

/**
 * MaterialService works with Material (catalogue units entities and DTOs)
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
@PropertySource("classpath:service_layer.properties")
public class MaterialService {

    private MaterialRepository materialRepository;
    @Value("${model.entity.alias.material}")
    private String materialEntityAlias;

    @Autowired
    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public List<Material> getAll () {
        List<Material> allMaterials = materialRepository.findAll();
        if (allMaterials.isEmpty()) {
            throw new EmptyListException(materialEntityAlias);
        }
        return allMaterials;
    }

    //findById: items.stream().findAny().map((e) -> items).orElseThrow(NotFoundException::new);

    public Material getById (Long id) throws ObjectNotFoundException {
        return materialRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(materialEntityAlias, id));
    }

    public Material create (Material material) {
        return materialRepository.save(material);
    }

    public Material update (Material modifiedMaterial) {
        return materialRepository.save(modifiedMaterial);
    }

    public void delete (Long id) {
        materialRepository.deleteById(id);
    }

}
