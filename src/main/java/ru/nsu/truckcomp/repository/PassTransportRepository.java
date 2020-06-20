package ru.nsu.truckcomp.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.truckcomp.model.PassengerTransport;

public interface PassTransportRepository extends CrudRepository<PassengerTransport, Integer> {

}
