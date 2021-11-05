package ru.goryachev.multichief.inventory.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.inventory.exception.MultiChiefEmptyListException;
import ru.goryachev.multichief.inventory.exception.MultiChiefObjectNotFoundException;
import ru.goryachev.multichief.inventory.model.dto.common.ImOrderCommonDto;
import ru.goryachev.multichief.inventory.model.entity.ImOrder;
import ru.goryachev.multichief.inventory.model.entity.Material;
import ru.goryachev.multichief.inventory.repository.BomRepository;
import ru.goryachev.multichief.inventory.repository.ImOrderRepository;
import ru.goryachev.multichief.inventory.service.converter.ImOrderConverter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ImOrderService (internal material order service) works with ImOrder (entities and DTOs)
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
@PropertySource("classpath:service_layer.properties")
public class ImOrderService {

    private ImOrderRepository imOrderRepository;
    private BomRepository bomRepository;
    private ImOrderConverter imOrderConverter;

    @Value("${model.entity.alias.imorder}")
    private String imOrderEntityAlias;
    @Value("${model.entity.alias.bom}")
    private String bomEntityAlias;

    @Autowired
    public ImOrderService(ImOrderRepository imOrderRepository, BomRepository bomRepository, ImOrderConverter imOrderConverter) {
        this.imOrderRepository = imOrderRepository;
        this.bomRepository = bomRepository;
        this.imOrderConverter = imOrderConverter;
    }

    public List<ImOrderCommonDto> getAll () throws MultiChiefEmptyListException {
        List<ImOrder> allImOrders = imOrderRepository.findAll();
        if (allImOrders.isEmpty()) {
            throw new MultiChiefEmptyListException(imOrderEntityAlias);
        }
        return allImOrders.stream().map(imOrderConverter::entityToDto).collect(Collectors.toList());
    }

    public Map<String, Object> create (ImOrderCommonDto imOrderCommonDto) throws MultiChiefObjectNotFoundException {
        if(!bomRepository.existsById(imOrderCommonDto.getBomId())){
            throw new MultiChiefObjectNotFoundException(bomEntityAlias, imOrderCommonDto.getBomId());
        }
        ImOrder imOrder = imOrderConverter.dtoToEntity(imOrderCommonDto);

        ImOrder savedimOrder = imOrderRepository.save(imOrder);
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("result", imOrderEntityAlias +" " + "was saved in DB");
        responseBody.put("id", savedimOrder.getId());
        responseBody.put("order time", savedimOrder.getOrderTime());
        return responseBody;
    }

    public ImOrder update (ImOrder modifiedImOrder) {
        return imOrderRepository.save(modifiedImOrder);
    }

    public void delete (Long id) {
        imOrderRepository.deleteById(id);
    }

}
