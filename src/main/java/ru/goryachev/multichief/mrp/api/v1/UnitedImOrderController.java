package ru.goryachev.multichief.mrp.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.goryachev.multichief.mrp.model.dto.request.ItemRequestDto;
import ru.goryachev.multichief.mrp.model.dto.PreformDto;
import ru.goryachev.multichief.mrp.model.dto.projection.ItemProjection;
import ru.goryachev.multichief.mrp.model.entity.ImOrder;
import ru.goryachev.multichief.mrp.model.entity.ImOrderItem;
import ru.goryachev.multichief.mrp.service.implementation.ImOrderItemService;
import ru.goryachev.multichief.mrp.service.implementation.ImOrderService;
import ru.goryachev.multichief.mrp.service.implementation.PreformImOrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class UnitedImOrderController {

    private ImOrderService imOrderService;
    private ImOrderItemService imOrderItemService;
    private PreformImOrderService preformImOrderService;

    @Autowired
    public UnitedImOrderController(ImOrderService imOrderService, ImOrderItemService imOrderItemService, PreformImOrderService preformImOrderService) {
        this.imOrderService = imOrderService;
        this.imOrderItemService = imOrderItemService;
        this.preformImOrderService = preformImOrderService;
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

    @DeleteMapping("{imOrderId}")//remove id and implement deleteAllBy
    public ResponseEntity<Throwable> delete (@PathVariable Long imOrderId) {
        imOrderService.delete(imOrderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * getOrderPreform returns PreformImOrderResponseDto (implementation of PreformDto) - a preform of order of materials.
     * The preform can be used by the consumer (other microservice) for preparing ready-to-use document (ViewModel).
     */
    @GetMapping("{imOrderId}")
    public ResponseEntity<PreformDto> getPreformImOrder (@PathVariable Long imOrderId) {
        return new ResponseEntity<>(preformImOrderService.getPreform(imOrderId), HttpStatus.OK);
    }

    @GetMapping("{imOrderId}/items")
    public ResponseEntity<List<ItemProjection>> getAllItems (@PathVariable Long imOrderId) throws Exception {
        return new ResponseEntity<>(imOrderItemService.getAllByImOrderId(imOrderId), HttpStatus.OK);
    }

    @PostMapping("{imOrderId}/items")
    public ResponseEntity<ImOrderItem> createItems (@PathVariable Long imOrderId, @RequestBody ItemRequestDto itemRequestDto) {
        return new ResponseEntity<>(imOrderItemService.save(imOrderId, itemRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("{imOrderId}/items")
    public ResponseEntity<ImOrderItem> updateItems (@PathVariable Long imOrderId, @RequestBody ItemRequestDto modifiedItemDto) {
        return new ResponseEntity<>(imOrderItemService.save(imOrderId, modifiedItemDto), HttpStatus.CREATED);
    }

    @DeleteMapping("{imOrderId}/items/{materialId}")//remove id and implement deleteAllBy
    public ResponseEntity<Throwable> deleteItems (@PathVariable Long imOrderId, @PathVariable Long materialId) {
        imOrderItemService.delete(imOrderId, materialId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
