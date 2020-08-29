package ru.nsu.truckcomp.controller.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/drivers")
    public String getDrivers(Map<String, Object> model) {
        model.put("drivers", driverRepository.findAll());
        model.put("brigades", brigadeRepository.findAll());
        model.put("transport", transportRepository.findAll());
        model.put("employees", employeeRepository.findAll());
        return "tables/drivers";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/addDriver")
    public String addDriver(@RequestParam String name,
                            @RequestParam Integer brigade,
                            @RequestParam Integer transport,
                            @RequestParam Integer boss,
                            Map<String, Object> model) {
        Driver driver = new Driver(name, "водитель",
                transportRepository.findById(transport).get(),
                brigadeRepository.findByBrigadeId(brigade),
                employeeRepository.findByEmpId(boss));
        driverRepository.save(driver);
        getDrivers(model);
        return "tables/drivers";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/deleteDriver")
    public String deleteDriver(@RequestParam Integer empId,
                               Map<String, Object> model) {
        employeeRepository.delete(employeeRepository.findByEmpId(empId));
        getDrivers(model);
        return "tables/drivers";
    }

}
