package ru.goryachev.multichief.inventory.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.inventory.exception.MultiChiefObjectNotFoundException;
import ru.goryachev.multichief.inventory.model.dto.response.PreformImOrderResponseDto;
import ru.goryachev.multichief.inventory.model.entity.ImOrder;
import ru.goryachev.multichief.inventory.repository.ImOrderItemRepository;
import ru.goryachev.multichief.inventory.repository.ImOrderRepository;
import ru.goryachev.multichief.inventory.service.PreformService;

import javax.transaction.Transactional;

/**
 * PreformImOrderService gets ImOrder, ImOrderItem (entities) and converts to ImOrderResponseDTO (preform of internal material order with items).
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
@PropertySource("classpath:service_layer.properties")
public class PreformImOrderService implements PreformService {

    private ImOrderItemRepository imOrderItemRepository;
    private ImOrderRepository imOrderRepository;
    @Value("${model.entity.alias.imorder}")
    private String imOrderEntityAlias;

    @Autowired
    public PreformImOrderService(ImOrderItemRepository imOrderItemRepository, ImOrderRepository imOrderRepository) {
        this.imOrderItemRepository = imOrderItemRepository;
        this.imOrderRepository = imOrderRepository;
    }

    @Override
    @Transactional
    public PreformImOrderResponseDto getPreform (Long imOrderId) throws MultiChiefObjectNotFoundException {

        ImOrder imOrder = imOrderRepository.findById(imOrderId).orElseThrow(() -> new MultiChiefObjectNotFoundException(imOrderEntityAlias, imOrderId));

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
