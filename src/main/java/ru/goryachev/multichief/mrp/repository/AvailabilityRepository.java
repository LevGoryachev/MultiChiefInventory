package ru.goryachev.multichief.mrp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.goryachev.multichief.mrp.model.dto.projection.ItemProjection;
import ru.goryachev.multichief.mrp.model.entity.Availability;

import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository <Availability, Long> {

   void deleteByWarehouseIdAndMaterialId(Long bomId, Long materialId);

   List<ItemProjection> findByWarehouseId(Long warehouseId); //DTO projection materials with quantities

}
