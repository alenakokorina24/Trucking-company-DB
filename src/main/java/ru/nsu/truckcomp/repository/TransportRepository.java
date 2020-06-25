package ru.nsu.truckcomp.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.nsu.truckcomp.model.Transport;

@Transactional
public interface TransportRepository extends TransportBaseRepository<Transport> {

}
