package ru.goryachev.multichief.inventory.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.inventory.exception.EmptyListException;
import ru.goryachev.multichief.inventory.model.entity.Bom;
import ru.goryachev.multichief.inventory.repository.BomRepository;

import java.util.List;

/**
 * BomService works with Bom (Bill of material entities and DTOs)
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
@PropertySource("classpath:service_layer.properties")
public class BomService {


    private BomRepository bomRepository;
    @Value("${model.entity.alias.bom}")
    private String bomEntityAlias;

    @Autowired
    public BomService(BomRepository bomRepository) {
        this.bomRepository = bomRepository;
    }

    public List<Bom> getAll () {
        List<Bom> allBoms = bomRepository.findAll();
        if (allBoms.isEmpty()) {
            throw new EmptyListException(bomEntityAlias);
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
