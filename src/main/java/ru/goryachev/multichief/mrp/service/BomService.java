package ru.goryachev.multichief.mrp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.mrp.model.entity.Bom;
import ru.goryachev.multichief.mrp.repository.BomRepository;

import java.util.List;

@Service
public class BomService {

    private BomRepository bomRepository;

    @Autowired
    public BomService(BomRepository bomRepository) {
        this.bomRepository = bomRepository;
    }

    public List<Bom> getAll () {
        return bomRepository.findAll();
    }

    public Bom getById (Long id) {
        return bomRepository.findById(id).get();
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
