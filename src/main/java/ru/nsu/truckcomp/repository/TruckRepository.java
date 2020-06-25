package ru.nsu.truckcomp.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.nsu.truckcomp.model.Truck;

@Transactional
public interface TruckRepository extends TransportBaseRepository<Truck> {

}
