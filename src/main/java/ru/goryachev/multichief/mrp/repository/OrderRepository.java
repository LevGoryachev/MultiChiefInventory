package ru.goryachev.multichief.mrp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.goryachev.multichief.mrp.model.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository <Order, Long> {

}
