package ru.nsu.truckcomp.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.truckcomp.model.ServiceStaff;

import java.util.List;

@Transactional
public interface ServiceStaffRepository extends EmployeeBaseRepository<ServiceStaff> {
    List<ServiceStaff> findAll(Pageable pageable);
}