package ru.nsu.truckcomp.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.truckcomp.model.DecommissionedTransport;

public interface DecTransportRepository extends CrudRepository<DecommissionedTransport, Integer> {

}
