package org.ebs.server.controller;

import org.ebs.shared.server.entity.Article;
import org.ebs.shared.server.entity.Graph;
import org.ebs.shared.server.entity.Heatmap;
import org.ebs.shared.server.entity.Resource;
import org.ebs.shared.server.hibernate.dao.ArticleDao;
import org.ebs.shared.server.hibernate.dao.GraphDao;
import org.ebs.shared.server.hibernate.dao.HeatmapDao;
import org.ebs.shared.server.hibernate.dao.ResourceDao;
import org.ebs.shared.server.util.GraphCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class IndexController {
    private final ResourceDao resourceDao;
    private final GraphDao graphDao;
    private final HeatmapDao heatmapDao;
    private final ArticleDao articleDao;

    @Autowired
    public IndexController(ResourceDao resourceDao, GraphDao graphDao, HeatmapDao heatmapDao, ArticleDao articleDao) {
        this.resourceDao = resourceDao;
        this.graphDao = graphDao;
        this.heatmapDao = heatmapDao;
        this.articleDao = articleDao;
    }


    @GetMapping("/")
    public String main(Model model) {
        GraphCreator graphCreator = new GraphCreator();
        Graph crimeGraphDb = graphDao.findByType("crime");
        Graph covidGraphDb = graphDao.findByType("covid");
        Graph warningGraphDb = graphDao.findByType("warning");
        List<Heatmap> heatmapsDb = heatmapDao.findAll();


        String crimeGraph = graphCreator.createStandardGraph("crimeChart", "line", crimeGraphDb.getLabels(), crimeGraphDb.getTitleLabel(), crimeGraphDb.getData());
        String covidGraph = graphCreator.createStandardGraph("covidCasesChart", "line", covidGraphDb.getLabels(), covidGraphDb.getTitleLabel(), covidGraphDb.getData());
        String warningGraph = graphCreator.createStandardGraph("warningChart", "bar", warningGraphDb.getLabels(), warningGraphDb.getTitleLabel(), warningGraphDb.getData());
        String heatmapGraph = graphCreator.createHeatmapGraph(heatmapsDb);


        List<Resource> resources = resourceDao.findAll();
        List<Article> articles = articleDao.findAll()
                .stream().sorted(Comparator.comparing(Article::getCreatedOn).reversed())
                .collect(Collectors.toList())
                .subList(0, 2);
        model.addAttribute("resources", resources);
        model.addAttribute("articles", articles);
        model.addAttribute("crimeGraph", crimeGraph);
        model.addAttribute("covidGraph", covidGraph);
        model.addAttribute("warningGraph", warningGraph);
        model.addAttribute("heatmapGraph", heatmapGraph);
        return "index"; //view
    }
}
