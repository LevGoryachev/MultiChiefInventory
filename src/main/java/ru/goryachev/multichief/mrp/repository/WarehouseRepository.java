package ru.goryachev.multichief.mrp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.goryachev.multichief.mrp.model.entity.Warehouse;

@Repository
public interface WarehouseRepository extends JpaRepository <Warehouse, Long> {

}
