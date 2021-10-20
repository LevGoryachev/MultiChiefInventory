package ru.goryachev.multichief.mrp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.mrp.model.entity.ImOrder;
import ru.goryachev.multichief.mrp.repository.ImOrderRepository;

import java.util.List;

/**
 * ImOrderService (internal material order service) works with ImOrder (entities and DTOs)
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
public class ImOrderService {

    private ImOrderRepository imOrderRepository;

    @Autowired
    public ImOrderService(ImOrderRepository imOrderRepository) {
        this.imOrderRepository = imOrderRepository;
    }

    public List<ImOrder> getAll () {
        return imOrderRepository.findAll();
    }

    public ImOrder create (ImOrder imOrder) {
        return imOrderRepository.save(imOrder);
    }

    public ImOrder update (ImOrder modifiedImOrder) {
        return imOrderRepository.save(modifiedImOrder);
    }

    public void delete (Long id) {
        imOrderRepository.deleteById(id);
    }

}
