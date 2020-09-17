package ru.nsu.truckcomp.controller.queries;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.PersistenceException;
import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import java.util.*;

@Controller
public class NativeQueryController {
    @GetMapping("/nativeQuery")
    public String getData(@RequestParam String queryText,
                          Map<String, Object> model) {
        try (Session session = DAO.getSession()) {
            Query query = session.createNativeQuery(queryText, Tuple.class);
            List<Tuple> resultList = query.getResultList();
            List<Object[]> values = new ArrayList<>();
            Set<String> columns = new HashSet<>();
            for (Tuple row : resultList) {
                values.add(row.toArray());
                List<TupleElement<?>> elements = row.getElements();
                for (TupleElement<?> element : elements) {
                    System.out.println(element);
                    columns.add(element.getAlias());
                }
            }
            model.put("columnNames", columns);
            model.put("objects", values.toArray());
        } catch (PersistenceException e) {
            model.put("message", "Error: " + e.getCause().getMessage() + ". Please try again.");
            return "/error";
        }
        return "query/nativeQuery";
    }
}
