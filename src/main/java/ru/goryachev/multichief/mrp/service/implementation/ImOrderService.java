package ru.goryachev.multichief.mrp.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.mrp.exception.EmptyListException;
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
    private final String ENTITY_TYPE_NAME = "ImOrder";

    @Autowired
    public ImOrderService(ImOrderRepository imOrderRepository) {
        this.imOrderRepository = imOrderRepository;
    }

    public List<ImOrder> getAll () {
        List<ImOrder> allImOrders = imOrderRepository.findAll();
        if (allImOrders.isEmpty()) {
            throw new EmptyListException(ENTITY_TYPE_NAME);
        }
        return allImOrders;
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
