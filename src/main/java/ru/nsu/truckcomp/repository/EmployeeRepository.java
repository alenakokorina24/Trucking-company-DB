package ru.nsu.truckcomp.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.truckcomp.model.Employee;

import java.util.List;

@Transactional
public interface EmployeeRepository extends EmployeeBaseRepository<Employee> {
    Employee findByEmpId(int id);

    List<Employee> findByBoss_EmpId(int is);

    List<Employee> findByBossNull();

    List<Employee> findAll(Pageable pageable);
}

