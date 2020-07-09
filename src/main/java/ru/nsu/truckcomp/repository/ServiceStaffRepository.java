package ru.nsu.truckcomp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.truckcomp.model.ServiceStaff;

@Transactional
public interface ServiceStaffRepository extends EmployeeBaseRepository<ServiceStaff> {

}