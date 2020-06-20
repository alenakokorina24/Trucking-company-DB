package ru.nsu.truckcomp.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.truckcomp.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}

