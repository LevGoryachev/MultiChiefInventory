package ru.goryachev.multichief.mrp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.goryachev.multichief.mrp.model.entity.BomItem;

@Repository
public interface BomItemRepository extends JpaRepository <BomItem, Long> {

   void deleteByBomIdAndMaterialId(Long bom_id, Long material_id);

}
