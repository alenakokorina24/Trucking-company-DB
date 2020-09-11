package ru.nsu.truckcomp.controller.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.model.Transport;
import ru.nsu.truckcomp.repository.TransportRepository;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Controller
public class TransportInfoController {
    @Autowired
    private TransportRepository transportRepository;

    private final int ROWS_PER_PAGE = 12;

    @GetMapping("/acqDec")
    public String getTransportInfo(@RequestParam(required = false) Date start,
                                   @RequestParam(required = false) Date end,
                                   @RequestParam(value = "page", defaultValue = "1") int pageNumber,
                                   Map<String, Object> model) {
        Pageable sorted = PageRequest.of(pageNumber - 1, ROWS_PER_PAGE, Sort.by("id").ascending());
        List<Transport> transport = transportRepository.findByAcquirementDateAfterAndAcquirementDateBeforeOrDecommissionDateAfterAndDecommissionDateBefore(start, end, start, end, sorted);
        model.put("hasPrev", pageNumber > 1);
        model.put("prev", pageNumber - 1);
        model.put("hasNext", (pageNumber * ROWS_PER_PAGE) < transport.size());
        model.put("next", pageNumber + 1);
        model.put("transport", transport);
        model.put("start", start);
        model.put("end", end);
        return "query/acqDec";
    }
}
