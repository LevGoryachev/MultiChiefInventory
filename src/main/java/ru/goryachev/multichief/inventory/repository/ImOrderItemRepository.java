package ru.goryachev.multichief.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.goryachev.multichief.inventory.model.dto.projection.ItemProjection;
import ru.goryachev.multichief.inventory.model.entity.ImOrderItem;

import java.util.List;

@Repository
public interface ImOrderItemRepository extends JpaRepository <ImOrderItem, Long> {
   void deleteByImOrderIdAndMaterialId(Long imOrderId, Long materialId);
   List<ItemProjection> findByImOrderId(Long imOrderId); //using DTO projection materials with quantities (ru.goryachev.multichief.inventory.model.dto.projection)
}
