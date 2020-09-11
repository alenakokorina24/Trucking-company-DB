package ru.nsu.truckcomp.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.nsu.truckcomp.model.Mileage;
import ru.nsu.truckcomp.model.Ride;
import ru.nsu.truckcomp.model.TransportRoute;

import java.sql.Date;
import java.util.List;

public interface RideRepository extends CrudRepository<Ride, Integer> {
    @Query(value = "SELECT new ru.nsu.truckcomp.model.TransportRoute(ri.transport.id, ri.route.routeId, ri.route.routeLength) FROM Ride ri")
    List<TransportRoute> getRoutesDistribution(Pageable pageable);

    Ride findByRideId(int id);

    @Query(value = "SELECT new ru.nsu.truckcomp.model.Ride(r.route, r.transport, r.date) FROM Ride r " +
            "INNER JOIN Truck t ON r.transport.id=t.id " +
            "WHERE r.date >= ?1 AND r.date <= ?2")
    List<Ride> getTruckRides(Date start, Date end, Pageable pageable);


    // select mileage by transport
    @Query(value = "SELECT new ru.nsu.truckcomp.model.Mileage(ri.transport.id, SUM(ri.route.routeLength)) FROM Ride ri " +
            "WHERE ri.transport.id = ?1 AND ri.date = ?2 " +
            "GROUP BY ri.transport.id")
    List<Mileage> getTransportDayMileage(int id, Date day, Pageable pageable);

    @Query(value = "SELECT new ru.nsu.truckcomp.model.Mileage(ri.transport.id, SUM(ri.route.routeLength)) FROM Ride ri " +
            "WHERE ri.transport.id = ?1 AND TO_CHAR(ri.date, 'YYYY-MM') = ?2 " +
            "GROUP BY ri.transport.id")
    List<Mileage> getTransportMonthMileage(int id, String month, Pageable pageable);

    @Query(value = "SELECT new ru.nsu.truckcomp.model.Mileage(ri.transport.id, SUM(ri.route.routeLength)) FROM Ride ri " +
            "WHERE ri.transport.id = ?1 AND YEAR(ri.date) = ?2 " +
            "GROUP BY ri.transport.id")
    List<Mileage> getTransportYearMileage(int id, int year, Pageable pageable);

    // select mileage for passenger transport only
    @Query(value = "SELECT new ru.nsu.truckcomp.model.Mileage(ri.transport.id, SUM(ri.route.routeLength)) FROM Ride ri " +
            "INNER JOIN PassengerTransport p ON p.id=ri.transport.id " +
            "WHERE ri.date = ?1 " +
            "GROUP BY ri.transport.id")
    List<Mileage> getPassengerTransportDayMileage(Date day, Pageable pageable);

    @Query(value = "SELECT new ru.nsu.truckcomp.model.Mileage(ri.transport.id, SUM(ri.route.routeLength)) FROM Ride ri " +
            "INNER JOIN PassengerTransport p ON p.id=ri.transport.id " +
            "WHERE TO_CHAR(ri.date, 'YYYY-MM') = ?1 " +
            "GROUP BY ri.transport.id")
    List<Mileage> getPassengerTransportMonthMileage(String month, Pageable pageable);

    @Query(value = "SELECT new ru.nsu.truckcomp.model.Mileage(ri.transport.id, SUM(ri.route.routeLength)) FROM Ride ri " +
            "INNER JOIN PassengerTransport p ON p.id=ri.transport.id " +
            "WHERE YEAR(ri.date) = ?1 " +
            "GROUP BY ri.transport.id")
    List<Mileage> getPassengerTransportYearMileage(int year, Pageable pageable);

    // select mileage for trucks only
    @Query(value = "SELECT new ru.nsu.truckcomp.model.Mileage(ri.transport.id, SUM(ri.route.routeLength)) FROM Ride ri " +
            "INNER JOIN Truck t ON t.id=ri.transport.id " +
            "WHERE ri.date = ?1 " +
            "GROUP BY ri.transport.id")
    List<Mileage> getTruckDayMileage(Date day, Pageable pageable);

    @Query(value = "SELECT new ru.nsu.truckcomp.model.Mileage(ri.transport.id, SUM(ri.route.routeLength)) FROM Ride ri " +
            "INNER JOIN Truck t ON t.id=ri.transport.id " +
            "WHERE TO_CHAR(ri.date, 'YYYY-MM') = ?1 " +
            "GROUP BY ri.transport.id")
    List<Mileage> getTruckMonthMileage(String month, Pageable pageable);

    @Query(value = "SELECT new ru.nsu.truckcomp.model.Mileage(ri.transport.id, SUM(ri.route.routeLength)) FROM Ride ri " +
            "INNER JOIN Truck t ON t.id=ri.transport.id " +
            "WHERE YEAR(ri.date) = ?1 " +
            "GROUP BY ri.transport.id")
    List<Mileage> getTruckYearMileage(int year, Pageable pageable);

    List<Ride> findAll(Pageable pageable);
}
