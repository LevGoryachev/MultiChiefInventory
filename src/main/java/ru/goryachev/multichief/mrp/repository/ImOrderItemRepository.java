package ru.goryachev.multichief.mrp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.goryachev.multichief.mrp.model.dto.projection.ItemProjection;
import ru.goryachev.multichief.mrp.model.entity.BomItem;
import ru.goryachev.multichief.mrp.model.entity.ImOrderItem;

import java.util.List;

@Repository
public interface ImOrderItemRepository extends JpaRepository <ImOrderItem, Long> {

   void deleteByImOrderIdAndMaterialId(Long bomId, Long materialId);

   List<ItemProjection> findByImOrderId(Long bomId); //using DTO projection materials with quantities (ru.goryachev.multichief.mrp.model.dto.projection)

}
