package org.ebs.rest.controller;

import org.ebs.shared.server.entity.Heatmap;
import org.ebs.shared.server.hibernate.dao.HeatmapDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class HeatmapController {
    private final HeatmapDao heatmapDao;

    @Autowired
    public HeatmapController(HeatmapDao heatmapDao) {
        this.heatmapDao = heatmapDao;
    }

    @GetMapping("/heatmaps")
    public List<Heatmap> getHeatMap() {
        return heatmapDao.findAll();
    }

    @PostMapping("/heatmaps/add")
    public Heatmap addHeatmapCoordinate() {
        Heatmap heatmap = new Heatmap();
        //e.g USA
        heatmap.setLongitude(-122.4194);
        heatmap.setLatitude(37.7749);
        heatmap.setWeight(5);

        return heatmapDao.save(heatmap);
    }
}
