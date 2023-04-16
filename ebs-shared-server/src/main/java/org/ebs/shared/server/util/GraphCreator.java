package org.ebs.shared.server.util;

import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.ebs.shared.server.entity.Heatmap;

import java.util.List;

public class GraphCreator {

    private final String graph = "var ctx = document.getElementById('%s').getContext('2d');\n" +
                                 "var crimeChart = new Chart(ctx, {\n" +
                                 "                            type: '%s',\n" +
                                 "                            data: {\n" +
                                 "                                labels: [%s],\n" +
                                 "                                datasets: [{\n" +
                                 "                                    label: '%s',\n" +
                                 "                                    data: [%s],\n" +
                                 "                                    backgroundColor: 'rgba(0, 188, 212, 0.2)',\n" +
                                 "                                    borderColor: 'rgba(0, 188, 212, 1)',\n" +
                                 "                                    borderWidth: 1\n" +
                                 "                                }]\n" +
                                 "                            },\n" +
                                 "                            options: {\n" +
                                 "                                scales: {\n" +
                                 "                                    yAxes: [{\n" +
                                 "                                        ticks: {\n" +
                                 "                                            beginAtZero: true\n" +
                                 "                                        }\n" +
                                 "                                    }]\n" +
                                 "                                }\n" +
                                 "                            }\n" +
                                 "                        });\n";


    private final String heatmap = "            const dbItems = %s;\n" +
                                   "                var features = dbItems.map(function(item) {\n" +
                                   "                    var coordinates = ol.proj.fromLonLat([item.longitude, item.latitude]);\n" +
                                   "                    var feature = new ol.Feature({\n" +
                                   "                        geometry: new ol.geom.Point(coordinates)\n" +
                                   "                    });\n" +
                                   "                    feature.set('weight', item.weight);\n" +
                                   "                    return feature;\n" +
                                   "                });\n" +
                                   "\n" +
                                   "                var source = new ol.source.Vector({\n" +
                                   "                    features: features\n" +
                                   "                });\n" +
                                   "\n" +
                                   "                var heatmap = new ol.layer.Heatmap({\n" +
                                   "                    source: source,\n" +
                                   "                    blur: 20,\n" +
                                   "                    radius: 15,\n" +
                                   "                    gradient: ['#00f', '#0ff', '#0f0', '#ff0', '#f00']\n" +
                                   "                });\n" +
                                   "\n" +
                                   "                var map = new ol.Map({\n" +
                                   "                    target: 'map',\n" +
                                   "                    layers: [\n" +
                                   "                        new ol.layer.Tile({\n" +
                                   "                            source: new ol.source.OSM()\n" +
                                   "                        }),\n" +
                                   "                        heatmap\n" +
                                   "                    ],\n" +
                                   "                    view: new ol.View({\n" +
                                   "                        center: ol.proj.fromLonLat([-3.7837, 52.1307]), // Set the initial center of the map\n" +
                                   "                        zoom: 5\n" +
                                   "                    })\n" +
                                   "                });\n";

    public String createStandardGraph(String element, String type, List<String> labels, String labelTitle,List<Double> data) {
        String graphLabels = StringUtils.wrap(StringUtils.join(labels, "\", \""), "\"").toString();
        StringBuilder dataString = new StringBuilder();
        for (Double d : data) {
            dataString.append(d).append(",");
        }
        String graphData = dataString.substring(0, dataString.length() - 1);

        String formattedGraph = String.format(graph, element, type, graphLabels, labelTitle, graphData);

        return formattedGraph;
    }

    public String createHeatmapGraph(List<Heatmap> heatmapsDb) {
        String objectsAsJson = new GsonBuilder().create().toJson(heatmapsDb);

        String formattedHeatmap = String.format(heatmap, objectsAsJson);
        return formattedHeatmap;
    }
}
