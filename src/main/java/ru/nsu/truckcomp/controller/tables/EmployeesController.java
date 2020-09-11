package ru.nsu.truckcomp.controller.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nsu.truckcomp.model.Employee;
import ru.nsu.truckcomp.repository.*;

import java.util.Map;

@Controller
public class EmployeesController {
    @Autowired
    private EmployeeRepository employeeRepository;

    private final int ROWS_PER_PAGE = 12;

    @GetMapping("/employees")
    public String getEmployees(Map<String, Object> model,
                               @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        long count = employeeRepository.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROWS_PER_PAGE) < count;
        Pageable sorted = PageRequest.of(pageNumber - 1, ROWS_PER_PAGE, Sort.by("driverId").ascending());
        model.put("employees", employeeRepository.findAll(sorted));
        model.put("hasPrev", hasPrev);
        model.put("prev", pageNumber - 1);
        model.put("hasNext", hasNext);
        model.put("next", pageNumber + 1);
        return "tables/employees";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/addEmployee")
    public String addEmployee(@RequestParam String name,
                              @RequestParam String position,
                              @RequestParam(required = false) Integer bossId,
                              Map<String, Object> model) {
        Employee boss = null;
        if (bossId != 0) {
            boss = employeeRepository.findByEmpId(bossId);
        }
        Employee employee = new Employee(name, position, boss);
        employeeRepository.save(employee);
        getEmployees(model, 1);
        return "tables/employees";
    }

    @PostMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam Integer empId,
                                 Map<String, Object> model) {
        employeeRepository.delete(employeeRepository.findByEmpId(empId));
        getEmployees(model, 1);
        return "tables/employees";
    }

}
