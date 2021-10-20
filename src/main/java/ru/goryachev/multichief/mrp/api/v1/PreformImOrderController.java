package ru.goryachev.multichief.mrp.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.goryachev.multichief.mrp.model.entity.ImOrder;
import ru.goryachev.multichief.mrp.service.ImOrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class PreformImOrderController {

    private ImOrderService imOrderService;

    @Autowired
    public PreformImOrderController(ImOrderService imOrderService) {
        this.imOrderService = imOrderService;
    }

    @GetMapping
    public ResponseEntity<List<ImOrder>> getAll () {
        return new ResponseEntity<>(imOrderService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ImOrder> create (@RequestBody ImOrder imOrder) {
        return new ResponseEntity<>(imOrderService.create(imOrder), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ImOrder> update (@RequestBody ImOrder modifiedImOrder) {
        return new ResponseEntity<>(imOrderService.update(modifiedImOrder), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Throwable> delete (@PathVariable Long id) {
        imOrderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
