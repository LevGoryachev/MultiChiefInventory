package ru.goryachev.multichief.inventory.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.goryachev.multichief.inventory.exception.MultiChiefEmptyListException;
import ru.goryachev.multichief.inventory.exception.MultiChiefObjectNotFoundException;
import ru.goryachev.multichief.inventory.model.dto.request.ItemRequestDto;
import ru.goryachev.multichief.inventory.model.dto.projection.ItemProjection;
import ru.goryachev.multichief.inventory.model.entity.Bom;
import ru.goryachev.multichief.inventory.model.entity.ImOrder;
import ru.goryachev.multichief.inventory.model.entity.ImOrderItem;
import ru.goryachev.multichief.inventory.model.entity.Material;
import ru.goryachev.multichief.inventory.repository.*;
import ru.goryachev.multichief.inventory.service.SpecialService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * SpecialImOrderItemService gets ItemDto (id and quantity of existing material) and converts to ImOrderItem (entity) for saving in DB;
 * provides CRUD for ImOrderItem.
 * @author Lev Goryachev
 * @version 1.1
 */

@Service
@PropertySource("classpath:service_layer.properties")
public class SpecialImOrderItemService implements SpecialService {

    private ImOrderItemRepository imOrderItemRepository;
    private ImOrderRepository imOrderRepository;
    private MaterialRepository materialRepository; //look for the cached data (where?)
    private BomRepository bomRepository;
    @Value("${model.entity.alias.imorder}")
    private String imOrderEntityAlias;
    @Value("${model.entity.alias.imorderitem}")
    private String imOrderItemEntityAlias;

    @Autowired
    public SpecialImOrderItemService(ImOrderItemRepository imOrderItemRepository, ImOrderRepository imOrderRepository, MaterialRepository materialRepository, BomRepository bomRepository) {
        this.imOrderItemRepository = imOrderItemRepository;
        this.imOrderRepository = imOrderRepository;
        this.materialRepository = materialRepository;
        this.bomRepository = bomRepository;
    }

    public List<ItemProjection> getAllByImOrderId(Long imOrderId) throws MultiChiefObjectNotFoundException, MultiChiefEmptyListException {

        if (!imOrderRepository.existsById(imOrderId)){
            throw new MultiChiefObjectNotFoundException(imOrderEntityAlias, imOrderId);
        }

        List<ItemProjection> imOrderItemList = imOrderItemRepository.findByImOrderId(imOrderId);

        if (imOrderItemList.isEmpty()) {
            throw new MultiChiefEmptyListException(imOrderItemEntityAlias);
        }
        return imOrderItemList;
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
