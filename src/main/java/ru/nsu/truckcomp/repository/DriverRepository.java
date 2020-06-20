package ru.nsu.truckcomp.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.truckcomp.model.Driver;

public interface DriverRepository extends CrudRepository<Driver, Integer> {

}

