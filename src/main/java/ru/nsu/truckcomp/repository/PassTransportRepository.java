package ru.nsu.truckcomp.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.truckcomp.model.Garage;
import ru.nsu.truckcomp.model.PassengerTransport;

import java.util.List;

@Transactional
public interface PassTransportRepository extends TransportBaseRepository<PassengerTransport> {
    @Query(value = "SELECT new ru.nsu.truckcomp.model.Garage(g.area) " +
            "FROM Garage g INNER JOIN PassengerTransport t ON g.garageId=t.garage.garageId ")
    List<Garage> getGarages(Pageable pageable);

    List<Garage> findAll(Pageable pageable);
}
