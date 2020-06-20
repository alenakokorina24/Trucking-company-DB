package ru.nsu.truckcomp.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.truckcomp.model.Ride;

public interface RideRepository extends CrudRepository<Ride, Integer> {

}
