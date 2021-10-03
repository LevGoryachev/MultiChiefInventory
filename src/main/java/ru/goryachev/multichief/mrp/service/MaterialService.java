package ru.goryachev.multichief.mrp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.mrp.model.entity.Material;
import ru.goryachev.multichief.mrp.repository.MaterialRepository;

import java.util.List;

@Service
public class MaterialService {

    private MaterialRepository materialRepository;

    @Autowired
    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public List<Material> getAll () {
        return materialRepository.findAll();
    }

    public Material getById (Long id) {
        return materialRepository.findById(id).get();
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
