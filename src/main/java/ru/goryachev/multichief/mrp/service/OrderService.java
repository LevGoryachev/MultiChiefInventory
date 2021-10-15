package ru.goryachev.multichief.mrp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.mrp.model.entity.Order;
import ru.goryachev.multichief.mrp.repository.OrderRepository;

import java.util.List;

/**
 * OrderService works with Order (Order entities and DTOs)
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAll () {
        return orderRepository.findAll();
    }

    public Order create (Order order) {
        return orderRepository.save(order);
    }

    public Order update (Order modifiedOrder) {
        return orderRepository.save(modifiedOrder);
    }

    public void delete (Long id) {
        orderRepository.deleteById(id);
    }

}
