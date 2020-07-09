package ru.nsu.truckcomp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.nsu.truckcomp.model.Employee;

@NoRepositoryBean
public interface EmployeeBaseRepository<T extends Employee> extends CrudRepository<T, Integer> {
    Iterable<T> findAll();
}
