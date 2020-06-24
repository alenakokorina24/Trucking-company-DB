package ru.nsu.truckcomp.usermanagement.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.truckcomp.usermanagement.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}