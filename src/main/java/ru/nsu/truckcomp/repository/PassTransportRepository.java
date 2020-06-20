package ru.nsu.truckcomp.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.nsu.truckcomp.model.PassengerTransport;

@Transactional
public interface PassTransportRepository extends TransportRepository<PassengerTransport> {

}
