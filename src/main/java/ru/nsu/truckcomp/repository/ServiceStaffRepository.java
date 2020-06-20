package ru.nsu.truckcomp.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.truckcomp.model.ServiceStaff;

public interface ServiceStaffRepository extends CrudRepository<ServiceStaff, Integer> {

}