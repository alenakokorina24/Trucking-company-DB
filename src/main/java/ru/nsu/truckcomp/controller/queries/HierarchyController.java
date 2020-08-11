package ru.nsu.truckcomp.controller.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.nsu.truckcomp.model.Employee;
import ru.nsu.truckcomp.repository.EmployeeRepository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class HierarchyController {
    @Autowired
    private EmployeeRepository employeeRepository;

    private final String PADDING = "            ";

    private String getSubordinates(Employee employee, String padding) {
        String tree = "";
        if (employee.getBoss() == null) {
            tree = "\n" + employee.getEmpId() + ". " + employee.getName() + " - " + employee.getPosition() + "\n";
        }
        Set<Employee> subordinates = employee.getSubordinates();
        for (Employee e: subordinates) {
            tree = tree.concat(padding + e.getEmpId() + ". " + e.getName() + " - " + e.getPosition() + "\n");
            tree = tree.concat(getSubordinates(e, padding + "               "));
        }
        return tree;
    }

    @GetMapping("/hierarchy")
    public String getHierarchy(Map<String, Object> model){
        List<Employee> bosses = employeeRepository.findByBossNull();
        String result = "";
        String padding = "              ";
        for (Employee b: bosses) {
            result = result.concat(getSubordinates(b, padding));
        }
        model.put("hierarchy", result);
        return "query/hierarchy";
    }
}
