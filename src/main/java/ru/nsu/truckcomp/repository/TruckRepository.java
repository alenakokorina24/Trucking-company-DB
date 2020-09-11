package ru.nsu.truckcomp.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.truckcomp.model.Garage;
import ru.nsu.truckcomp.model.Truck;

import java.util.List;

@Transactional
public interface TruckRepository extends TransportBaseRepository<Truck> {
    @Query(value = "SELECT new ru.nsu.truckcomp.model.Garage(g.area) " +
            "FROM Garage g INNER JOIN Truck t ON g.garageId=t.garage.garageId ")
    List<Garage> getGarages();

    List<Truck> findAll(Pageable pageable);
}
