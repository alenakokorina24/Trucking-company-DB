package ru.nsu.truckcomp.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.truckcomp.model.RepairList;

public interface RepairListRepository extends CrudRepository<RepairList, Integer> {

}
