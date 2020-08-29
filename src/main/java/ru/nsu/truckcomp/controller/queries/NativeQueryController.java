package ru.nsu.truckcomp.controller.queries;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.PersistenceException;
import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class NativeQueryController {
    @GetMapping("/nativeQuery")
    public String getData(@RequestParam String queryText,
                          Map<String, Object> model) {
        try (Session session = DAO.getSession()) {
            Query query = session.createNativeQuery(queryText, Tuple.class);
            List<Tuple> resultList = query.getResultList();
            Set<String> columns = new HashSet<>();
            for (Tuple row : resultList) {
                List<TupleElement<?>> elements = row.getElements();
                for (TupleElement<?> element : elements) {
                    columns.add(element.getAlias());
                }
            }
            Query query1 = session.createSQLQuery(queryText);
            List<Object[]> result = query1.list();
            model.put("columnNames", columns);
            model.put("objects", result.toArray());
        } catch (PersistenceException e) {
            model.put("message", "Error: " + e.getCause().getMessage() + ". Please try again.");
            return "/error";
        }
        return "query/nativeQuery";
    }
}
