package ru.nsu.truckcomp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.nsu.truckcomp.model.Ride;
import ru.nsu.truckcomp.model.TransportRoute;

import java.sql.Date;
import java.util.List;

public interface RideRepository extends CrudRepository<Ride, Integer> {
    @Query(value = "SELECT new ru.nsu.truckcomp.model.TransportRoute(ri.transport.id, ro.routeId, ro.routeLength) FROM Route ro INNER JOIN Ride ri ON ri.route.routeId=ro.routeId")
    List<TransportRoute> getRoutesDistribution();

    Iterable<Ride> findByDateAndTransport_Id(Date date, int id);
}
