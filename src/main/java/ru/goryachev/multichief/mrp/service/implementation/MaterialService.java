package ru.goryachev.multichief.mrp.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
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
public class MaterialService {

    private MaterialRepository materialRepository;
    private final String ENTITY_TYPE_NAME = "Material";

    @Autowired
    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public List<Material> getAll () {
        List<Material> allMaterials = materialRepository.findAll();
        if (allMaterials.isEmpty()) {
            throw new EmptyListException(ENTITY_TYPE_NAME);
        }
        return allMaterials;
    }

    //findById: items.stream().findAny().map((e) -> items).orElseThrow(NotFoundException::new);

    public Material getById (Long id) throws ObjectNotFoundException {
        return materialRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(ENTITY_TYPE_NAME, id));
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
