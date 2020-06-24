package ru.nsu.truckcomp.usermanagement.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.truckcomp.usermanagement.entity.Privilege;

public interface PrivilegeRepository extends CrudRepository<Privilege, Integer> {
    Privilege findByName(String name);
}