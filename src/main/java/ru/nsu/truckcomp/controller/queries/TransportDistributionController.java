package ru.nsu.truckcomp.controller.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.model.Driver;
import ru.nsu.truckcomp.model.Transport;
import ru.nsu.truckcomp.model.TransportArea;
import ru.nsu.truckcomp.repository.TransportRepository;

import java.util.List;
import java.util.Map;

@Controller
public class TransportDistributionController {
    @Autowired
    private TransportRepository transportRepository;

    private final int ROWS_PER_PAGE = 12;

    @GetMapping("/transportDistribution")
    public String getRoutesInfo(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                                Map<String, Object> model) {
        Pageable sorted = PageRequest.of(pageNumber - 1, ROWS_PER_PAGE, Sort.by("id").ascending());
        List<TransportArea> distr = transportRepository.getTransportDistribution(sorted);
        model.put("transport", distr);
        model.put("hasPrev", pageNumber > 1);
        model.put("prev", pageNumber - 1);
        model.put("hasNext", (pageNumber * ROWS_PER_PAGE) < distr.size());
        model.put("next", pageNumber + 1);
        return "query/transportDistribution";
    }
}
