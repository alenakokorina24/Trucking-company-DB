package ru.nsu.truckcomp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.nsu.truckcomp.model.Ride;
import ru.nsu.truckcomp.model.TransportRoute;

import java.util.Date;
import java.util.List;

public interface RideRepository extends CrudRepository<Ride, Integer> {
    @Query(value = "SELECT new ru.nsu.truckcomp.model.TransportRoute(ri.transport.id, ro.routeId, ro.routeLength) FROM Route ro INNER JOIN Ride ri ON ri.route.routeId=ro.routeId")
    List<TransportRoute> getRoutesDistribution();

    Iterable<Ride> findByDateAndTransport_Id(Date date, int id);

    Ride findByRideId(int id);

    @Query(value = "SELECT new ru.nsu.truckcomp.model.Ride(r.route, r.transport, r.date) " +
            "FROM Ride r INNER JOIN Truck t ON r.transport.id=t.id " +
            "WHERE r.date >= ?1 AND r.date <= ?2")
    List<Ride> getTruckRides(Date start, Date end);
}
