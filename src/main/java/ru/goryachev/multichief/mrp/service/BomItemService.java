package ru.goryachev.multichief.mrp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.mrp.model.entity.BomItem;
import ru.goryachev.multichief.mrp.repository.BomItemRepository;

import java.util.List;

@Service
public class BomItemService {

    private BomItemRepository bomItemRepository;

    @Autowired
    public BomItemService(BomItemRepository bomItemRepository) {
        this.bomItemRepository = bomItemRepository;
    }

    public List<BomItem> getAll () {
        return bomItemRepository.findAll();
    }

    public BomItem getById (Long id) {
        return bomItemRepository.findById(id).get();
    }

    public BomItem create (BomItem bomItem) {
        return bomItemRepository.save(bomItem);
    }

    public BomItem update (BomItem modifiedBomItem) {
        return bomItemRepository.save(modifiedBomItem);
    }

    public void delete (Long id) {
        bomItemRepository.deleteById(id);
    }

}
