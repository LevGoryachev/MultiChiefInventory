package ru.goryachev.multichief.inventory.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.inventory.exception.EmptyListException;
import ru.goryachev.multichief.inventory.model.entity.ImOrder;
import ru.goryachev.multichief.inventory.repository.ImOrderRepository;

import java.util.List;

/**
 * ImOrderService (internal material order service) works with ImOrder (entities and DTOs)
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
@PropertySource("classpath:service_layer.properties")
public class ImOrderService {

    private ImOrderRepository imOrderRepository;
    @Value("${model.entity.alias.imorder}")
    private String imOrderEntityAlias;

    @Autowired
    public ImOrderService(ImOrderRepository imOrderRepository) {
        this.imOrderRepository = imOrderRepository;
    }

    public List<ImOrder> getAll () {
        List<ImOrder> allImOrders = imOrderRepository.findAll();
        if (allImOrders.isEmpty()) {
            throw new EmptyListException(imOrderEntityAlias);
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
