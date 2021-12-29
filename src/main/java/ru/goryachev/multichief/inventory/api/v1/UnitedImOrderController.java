package ru.goryachev.multichief.inventory.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.goryachev.multichief.inventory.model.dto.CommonDto;
import ru.goryachev.multichief.inventory.model.dto.common.ImOrderCommonDto;
import ru.goryachev.multichief.inventory.model.dto.request.ItemRequestDto;
import ru.goryachev.multichief.inventory.model.dto.PreformDto;
import ru.goryachev.multichief.inventory.model.dto.projection.ItemProjection;
import ru.goryachev.multichief.inventory.service.implementation.SpecialImOrderItemService;
import ru.goryachev.multichief.inventory.service.implementation.StandardImOrderService;
import ru.goryachev.multichief.inventory.service.implementation.PreformImOrderService;

import javax.validation.Valid;
import java.util.List;

/**
 * MultiChiefInventory API: see app/swagger-ui/
 * @author Lev Goryachev
 * @version 1
 */

@RestController
@RequestMapping("/api/v1/orders")
public class UnitedImOrderController {

    private StandardImOrderService standardImOrderService;
    private SpecialImOrderItemService specialImOrderItemService;
    private PreformImOrderService preformImOrderService;

    @Autowired
    public UnitedImOrderController(StandardImOrderService standardImOrderService, SpecialImOrderItemService specialImOrderItemService, PreformImOrderService preformImOrderService) {
        this.standardImOrderService = standardImOrderService;
        this.specialImOrderItemService = specialImOrderItemService;
        this.preformImOrderService = preformImOrderService;
    }

    @GetMapping
    public ResponseEntity<List<CommonDto>> getAll () {
        return new ResponseEntity<>(standardImOrderService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create (@RequestBody @Valid ImOrderCommonDto imOrderCommonDto) {
        return new ResponseEntity<>(standardImOrderService.create(imOrderCommonDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> update (@RequestBody @Valid ImOrderCommonDto modifiedImOrder) {
        return new ResponseEntity<>(standardImOrderService.update(modifiedImOrder), HttpStatus.OK);
    }

    @DeleteMapping("{imOrderId}")//remove id and implement deleteAllBy
    public ResponseEntity<Object> delete (@PathVariable Long imOrderId) {
        return new ResponseEntity<>(standardImOrderService.delete(imOrderId), HttpStatus.OK);
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
    public ResponseEntity<List<ItemProjection>> getAllItems (@PathVariable Long imOrderId) {
        return new ResponseEntity<>(specialImOrderItemService.getAllByImOrderId(imOrderId), HttpStatus.OK);
    }

    @PostMapping("{imOrderId}/items")
    public ResponseEntity<Object> createItems (@PathVariable Long imOrderId, @RequestBody ItemRequestDto itemRequestDto) {
        return new ResponseEntity<>(specialImOrderItemService.save(imOrderId, itemRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("{imOrderId}/items")
    public ResponseEntity<Object> updateItems (@PathVariable Long imOrderId, @RequestBody ItemRequestDto modifiedItemDto) {
        return new ResponseEntity<>(specialImOrderItemService.save(imOrderId, modifiedItemDto), HttpStatus.CREATED);
    }

    @DeleteMapping("{imOrderId}/items/{materialId}")//remove id and implement deleteAllBy
    public ResponseEntity<Throwable> deleteItems (@PathVariable Long imOrderId, @PathVariable Long materialId) {
        specialImOrderItemService.delete(imOrderId, materialId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
