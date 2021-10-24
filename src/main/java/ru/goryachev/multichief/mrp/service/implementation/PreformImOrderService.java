package ru.goryachev.multichief.mrp.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.mrp.model.dto.preform.PreformImOrderResponseDto;
import ru.goryachev.multichief.mrp.model.entity.ImOrder;
import ru.goryachev.multichief.mrp.repository.ImOrderItemRepository;
import ru.goryachev.multichief.mrp.repository.ImOrderRepository;
import ru.goryachev.multichief.mrp.service.PreformService;

import javax.transaction.Transactional;

/**
 * PreformImOrderService gets ImOrder, ImOrderItem (entities) and converts to ImOrderResponseDTO (preform of internal material order with items).
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
public class PreformImOrderService implements PreformService {

    private ImOrderItemRepository imOrderItemRepository;
    private ImOrderRepository imOrderRepository;

    @Autowired
    public PreformImOrderService(ImOrderItemRepository imOrderItemRepository, ImOrderRepository imOrderRepository) {
        this.imOrderItemRepository = imOrderItemRepository;
        this.imOrderRepository = imOrderRepository;
    }

    @Override
    @Transactional
    public PreformImOrderResponseDto getPreform (Long imOrderId) {

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
}
