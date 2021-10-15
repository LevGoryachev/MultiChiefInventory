package ru.goryachev.multichief.mrp.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.goryachev.multichief.mrp.model.entity.Order;
import ru.goryachev.multichief.mrp.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAll () {
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> create (@RequestBody Order order) {
        return new ResponseEntity<>(orderService.create(order), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Order> update (@RequestBody Order modifiedOrder) {
        return new ResponseEntity<>(orderService.update(modifiedOrder), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Throwable> delete (@PathVariable Long id) {
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
