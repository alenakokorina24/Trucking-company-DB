package ru.nsu.truckcomp.repository;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface GovernanceRepository extends EmployeeBaseRepository<Governance> {
    Governance findByEmpId(int id);
}
