package ru.nsu.truckcomp.controller.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nsu.truckcomp.model.Brigade;
import ru.nsu.truckcomp.model.Driver;
import ru.nsu.truckcomp.repository.*;

import java.util.Map;

@Controller
public class DriversController {
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private BrigadeRepository brigadeRepository;

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private final int ROWS_PER_PAGE = 12;

    @GetMapping("/drivers")
    public String getDrivers(Map<String, Object> model,
                             @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        long count = driverRepository.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROWS_PER_PAGE) < count;
        Pageable sorted = PageRequest.of(pageNumber - 1, ROWS_PER_PAGE, Sort.by("empId").ascending());
        model.put("drivers", driverRepository.findAll(sorted));
        model.put("brigades", brigadeRepository.findAll());
        model.put("transport", transportRepository.findAll());
        model.put("employees", employeeRepository.findAll());
        model.put("hasPrev", hasPrev);
        model.put("prev", pageNumber - 1);
        model.put("hasNext", hasNext);
        model.put("next", pageNumber + 1);
        return "tables/drivers";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/addDriver")
    public String addDriver(@RequestParam String name,
                            @RequestParam Integer brigade,
                            @RequestParam Integer transport,
                            Map<String, Object> model) {
        Brigade empBrigade = brigadeRepository.findByBrigadeId(brigade);
        Driver driver = new Driver(name, "водитель",
                transportRepository.findById(transport).get(),
                empBrigade,
                employeeRepository.findByEmpId(empBrigade.getBrigadier().getEmpId()));
        driverRepository.save(driver);
        getDrivers(model, 1);
        return "tables/drivers";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/deleteDriver")
    public String deleteDriver(@RequestParam Integer empId,
                               Map<String, Object> model) {
        employeeRepository.delete(employeeRepository.findByEmpId(empId));
        getDrivers(model, 1);
        return "tables/drivers";
    }

}
