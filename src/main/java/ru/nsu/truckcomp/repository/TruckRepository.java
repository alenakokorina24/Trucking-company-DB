package ru.nsu.truckcomp.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.truckcomp.model.Truck;

public interface TruckRepository extends CrudRepository<Truck, Integer> {

}
