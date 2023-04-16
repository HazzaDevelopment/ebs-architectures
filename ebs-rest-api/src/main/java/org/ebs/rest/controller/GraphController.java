package org.ebs.rest.controller;

import org.ebs.shared.server.entity.Graph;
import org.ebs.shared.server.hibernate.dao.GraphDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class GraphController {
    private final GraphDao graphDao;

    @Autowired
    public GraphController(GraphDao graphDao) {
        this.graphDao = graphDao;
    }

    // I know its hacky using the type to determine which graph it is
    @GetMapping("/graph")
    public Graph getGraph(@RequestParam String type){
        Graph byType = graphDao.findByType(type);
        return byType;
    }

    @PostMapping("/graph")
    public void addCovidGraph(@RequestBody Graph graph) {
        graphDao.save(graph);
    }
}
