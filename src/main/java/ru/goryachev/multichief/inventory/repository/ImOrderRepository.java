package ru.goryachev.multichief.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.goryachev.multichief.inventory.model.entity.ImOrder;

@Repository
public interface ImOrderRepository extends JpaRepository <ImOrder, Long> {

}
