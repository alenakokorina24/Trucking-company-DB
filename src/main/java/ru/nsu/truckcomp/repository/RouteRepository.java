package ru.nsu.truckcomp.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.truckcomp.model.Route;

public interface RouteRepository extends CrudRepository<Route, Integer> {

}
