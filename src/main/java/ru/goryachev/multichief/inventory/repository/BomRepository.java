package ru.goryachev.multichief.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.goryachev.multichief.inventory.model.entity.Bom;

@Repository
public interface BomRepository extends JpaRepository <Bom, Long> {

}
