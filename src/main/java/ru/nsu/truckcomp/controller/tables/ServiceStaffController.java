package ru.nsu.truckcomp.controller.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nsu.truckcomp.model.ServiceStaff;
import ru.nsu.truckcomp.repository.*;

import java.util.Map;

@Controller
public class ServiceStaffController {
    @Autowired
    private ServiceStaffRepository serviceStaffRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BrigadeRepository brigadeRepository;

    private final int ROWS_PER_PAGE = 12;

    @GetMapping("/serviceStaff")
    public String getServiceStaff(Map<String, Object> model,
                                  @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        long count = serviceStaffRepository.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROWS_PER_PAGE) < count;
        Pageable sorted = PageRequest.of(pageNumber - 1, ROWS_PER_PAGE, Sort.by("empId").ascending());
        model.put("serviceStaff", serviceStaffRepository.findAll(sorted));
        model.put("brigades", brigadeRepository.findAll());
        model.put("employees", employeeRepository.findAll());
        model.put("hasPrev", hasPrev);
        model.put("prev", pageNumber - 1);
        model.put("hasNext", hasNext);
        model.put("next", pageNumber + 1);
        return "tables/serviceStaff";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/addServiceStaff")
    public String addServiceStaff(@RequestParam String name,
                                  @RequestParam String position,
                                  @RequestParam Integer brigade,
                                  @RequestParam Integer boss,
                                  Map<String, Object> model) {
        ServiceStaff serviceStaff = new ServiceStaff(name, position,
                brigadeRepository.findByBrigadeId(brigade),
                employeeRepository.findByEmpId(boss));
        serviceStaffRepository.save(serviceStaff);
        getServiceStaff(model, 1);
        return "tables/serviceStaff";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/deleteServiceStaff")
    public String deleteServiceStaff(@RequestParam Integer empId,
                                     Map<String, Object> model) {
        employeeRepository.delete(employeeRepository.findByEmpId(empId));
        getServiceStaff(model, 1);
        return "tables/serviceStaff";
    }

}
