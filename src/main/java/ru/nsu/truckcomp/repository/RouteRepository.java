package ru.nsu.truckcomp.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.nsu.truckcomp.model.Route;

import java.util.List;

public interface RouteRepository extends CrudRepository<Route, Integer> {
    Route findByRouteId(int id);

    List<Route> findAll(Pageable pageable);
}
