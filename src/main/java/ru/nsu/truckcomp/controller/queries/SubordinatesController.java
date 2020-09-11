package ru.nsu.truckcomp.controller.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.model.Employee;
import ru.nsu.truckcomp.model.RepairList;
import ru.nsu.truckcomp.repository.EmployeeRepository;

import java.util.List;
import java.util.Map;

@Controller
public class SubordinatesController {
    @Autowired
    private EmployeeRepository employeeRepository;

    private final int ROWS_PER_PAGE = 12;

    @GetMapping("/subordinates")
    public String getSubordinates(@RequestParam Integer employee,
                                  @RequestParam(value = "page", defaultValue = "1") int pageNumber,
                                  Map<String, Object> model) {
        Pageable sorted = PageRequest.of(pageNumber - 1, ROWS_PER_PAGE, Sort.by("empId").ascending());
        List<Employee> subs = employeeRepository.findByBoss_EmpId(employee, sorted);
        Employee boss = employeeRepository.findByEmpId(employee);
        model.put("subordinates", subs);
        model.put("employee", boss.getName());
        model.put("position", boss.getPosition());
        model.put("hasPrev", pageNumber > 1);
        model.put("prev", pageNumber - 1);
        model.put("hasNext", (pageNumber * ROWS_PER_PAGE) < subs.size());
        model.put("next", pageNumber + 1);
        return "query/subordinates";
    }
}