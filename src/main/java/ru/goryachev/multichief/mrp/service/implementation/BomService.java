package ru.goryachev.multichief.mrp.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.mrp.exception.EmptyListException;
import ru.goryachev.multichief.mrp.model.entity.Bom;
import ru.goryachev.multichief.mrp.model.entity.Material;
import ru.goryachev.multichief.mrp.repository.BomRepository;

import java.util.List;

/**
 * BomService works with Bom (Bill of material entities and DTOs)
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
public class BomService {

    private BomRepository bomRepository;
    private final String ENTITY_TYPE_NAME = "Bom";

    @Autowired
    public BomService(BomRepository bomRepository) {
        this.bomRepository = bomRepository;
    }

    public List<Bom> getAll () {
        List<Bom> allBoms = bomRepository.findAll();
        if (allBoms.isEmpty()) {
            throw new EmptyListException(ENTITY_TYPE_NAME);
        }
        return allBoms;
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