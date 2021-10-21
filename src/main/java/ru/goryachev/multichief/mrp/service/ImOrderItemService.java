package ru.goryachev.multichief.mrp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.mrp.model.dto.PreformImOrderResponseDto;
import ru.goryachev.multichief.mrp.model.dto.ItemRequestDto;
import ru.goryachev.multichief.mrp.model.entity.Bom;
import ru.goryachev.multichief.mrp.model.entity.ImOrder;
import ru.goryachev.multichief.mrp.model.entity.ImOrderItem;
import ru.goryachev.multichief.mrp.model.entity.Material;
import ru.goryachev.multichief.mrp.repository.*;

import javax.transaction.Transactional;

/**
 * ImOrderItemService gets ItemDto (id and quantity of existing material) and converts to ImOrderItem (entity) for saving in DB;
 * gets ImOrderItem (data from DB) and converts to ImOrderResponseDTO (internal material order with items).
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
public class ImOrderItemService {

    private ImOrderItemRepository imOrderItemRepository;
    private ImOrderRepository imOrderRepository;
    private MaterialRepository materialRepository; //look for the cached data (where?)
    private BomRepository bomRepository;

    @Autowired
    public ImOrderItemService(ImOrderItemRepository imOrderItemRepository, ImOrderRepository imOrderRepository, MaterialRepository materialRepository, BomRepository bomRepository) {
        this.imOrderItemRepository = imOrderItemRepository;
        this.imOrderRepository = imOrderRepository;
        this.materialRepository = materialRepository;
        this.bomRepository = bomRepository;
    }

    @Transactional
    public PreformImOrderResponseDto getImOrderResponseDto (Long imOrderId) {

        ImOrder imOrder = imOrderRepository.findById(imOrderId).get();

        PreformImOrderResponseDto preformImOrderResponseDto = new PreformImOrderResponseDto();
        preformImOrderResponseDto.setId(imOrderId);
        preformImOrderResponseDto.setBomId(imOrder.getBomId());
        preformImOrderResponseDto.setOrderTime(imOrder.getOrderTime());
        preformImOrderResponseDto.setPosted(imOrder.getPosted());
        preformImOrderResponseDto.setSent(imOrder.getSent());
        preformImOrderResponseDto.setStatusExecuted(imOrder.getStatusExecuted());
        preformImOrderResponseDto.setItems(imOrderItemRepository.findByImOrderId(imOrderId));
        return preformImOrderResponseDto;
    }

    @Transactional
    public ImOrderItem save (Long imOrderId, ItemRequestDto itemRequestDto) {

        ImOrder imOrder = imOrderRepository.getOne(imOrderId);
        Bom bom = bomRepository.getOne(imOrder.getBomId());
        Material material = materialRepository.getOne(itemRequestDto.getMaterialId());

        ImOrderItem imOrderItem = new ImOrderItem(bom, material, imOrder);
        imOrderItem.setQty(itemRequestDto.getQty());
        return imOrderItemRepository.save(imOrderItem);
    }

    @Transactional
    public void delete (Long imOrderId, Long materialId) {
       imOrderItemRepository.deleteByImOrderIdAndMaterialId(imOrderId, materialId);
    }
}
