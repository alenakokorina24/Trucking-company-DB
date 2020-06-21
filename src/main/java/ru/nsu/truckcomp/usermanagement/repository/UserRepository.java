package ru.nsu.truckcomp.usermanagement.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.truckcomp.usermanagement.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
