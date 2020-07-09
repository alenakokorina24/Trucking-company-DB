package ru.nsu.truckcomp.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.nsu.truckcomp.model.Employee;

@Transactional
public interface EmployeeRepository extends EmployeeBaseRepository<Employee> {
    Employee findByEmpId(int id);
}

