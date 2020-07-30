package ru.nsu.truckcomp.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.nsu.truckcomp.model.Driver;
import ru.nsu.truckcomp.model.Governance;

import java.util.List;

@Transactional
public interface GovernanceRepository extends EmployeeBaseRepository<Governance> {
    Governance findByEmpId(int id);
}
