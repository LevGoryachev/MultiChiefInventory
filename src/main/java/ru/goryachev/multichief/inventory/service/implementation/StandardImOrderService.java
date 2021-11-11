package ru.goryachev.multichief.inventory.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.inventory.exception.MultiChiefEmptyListException;
import ru.goryachev.multichief.inventory.exception.MultiChiefObjectNotFoundException;
import ru.goryachev.multichief.inventory.model.dto.CommonDto;
import ru.goryachev.multichief.inventory.model.dto.common.ImOrderCommonDto;
import ru.goryachev.multichief.inventory.model.entity.ImOrder;
import ru.goryachev.multichief.inventory.repository.BomRepository;
import ru.goryachev.multichief.inventory.repository.ImOrderRepository;
import ru.goryachev.multichief.inventory.service.StandardService;
import ru.goryachev.multichief.inventory.service.converter.ImOrderConverter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * StandardImOrderService (internal material order service) works with ImOrder (entities and DTOs)
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
@PropertySource("classpath:service_layer.properties")
public class StandardImOrderService implements StandardService {

    private ImOrderRepository imOrderRepository;
    private BomRepository bomRepository;
    private ImOrderConverter imOrderConverter;

    @Value("${model.entity.alias.imorder}")
    private String imOrderEntityAlias;
    @Value("${model.entity.alias.bom}")
    private String bomEntityAlias;

    @Autowired
    public StandardImOrderService(ImOrderRepository imOrderRepository, BomRepository bomRepository, ImOrderConverter imOrderConverter) {
        this.imOrderRepository = imOrderRepository;
        this.bomRepository = bomRepository;
        this.imOrderConverter = imOrderConverter;
    }

    @Override
    public List<CommonDto> getAll () throws MultiChiefEmptyListException {
        List<ImOrder> allImOrders = imOrderRepository.findAll();
        if (allImOrders.isEmpty()) {
            throw new MultiChiefEmptyListException(imOrderEntityAlias);
        }
        return allImOrders.stream().map(imOrderConverter::entityToDto).collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> create (CommonDto imOrderCommonDto) throws MultiChiefObjectNotFoundException {
        if(!bomRepository.existsById(((ImOrderCommonDto)imOrderCommonDto).getBomId())){
            throw new MultiChiefObjectNotFoundException(bomEntityAlias, ((ImOrderCommonDto)imOrderCommonDto).getBomId());
        }
        ImOrder imOrder = (ImOrder) imOrderConverter.dtoToEntity(imOrderCommonDto);

        ImOrder savedimOrder = imOrderRepository.save(imOrder);
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("result", imOrderEntityAlias + " " + "was saved in DB");
        responseBody.put("id", savedimOrder.getId());
        responseBody.put("order time", savedimOrder.getOrderTime());
        return responseBody;
    }

    @Override
    public Map<String, Object> update (CommonDto modifiedImOrder) throws MultiChiefObjectNotFoundException {
        if(!bomRepository.existsById(((ImOrderCommonDto)modifiedImOrder).getBomId())){
            throw new MultiChiefObjectNotFoundException(bomEntityAlias, ((ImOrderCommonDto)modifiedImOrder).getBomId());
        }
        ImOrder imOrder = (ImOrder) imOrderConverter.dtoToEntity(modifiedImOrder);

        ImOrder savedimOrder = imOrderRepository.save(imOrder);
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("result", imOrderEntityAlias + " " + "was updated");
        responseBody.put("id", savedimOrder.getId());
        responseBody.put("order time", savedimOrder.getOrderTime());
        return responseBody;
    }

    @Override
    public Map<String, Object> delete (Long id) {
        imOrderRepository.deleteById(id);
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("result", imOrderEntityAlias + " " + "with id" + " " + id + " " + "was deleted");
        return responseBody;
    }
}
