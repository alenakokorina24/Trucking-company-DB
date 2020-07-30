package ru.nsu.truckcomp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.nsu.truckcomp.model.Transport;

@NoRepositoryBean
public interface TransportBaseRepository<T extends Transport> extends CrudRepository<T, Integer> {
}

