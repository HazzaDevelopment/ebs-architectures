package org.ebs.shared.serverless.dao;

import org.ebs.shared.serverless.entity.Heatmap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HeatmapDao extends GenericDao<Heatmap>{

    public HeatmapDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Heatmap> findAll() {
        List<Heatmap> heatmapList = new ArrayList<>();
        try {
            String regQuery = "SELECT * from heatmap;";
            PreparedStatement pst = conn.prepareStatement(regQuery);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                double latitude = rs.getDouble("latitude");
                double longitude = rs.getDouble("longitude");
                int weight = rs.getInt("weight");

                Heatmap heatmap = new Heatmap();
                heatmap.setId(id);
                heatmap.setLatitude(latitude);
                heatmap.setLongitude(longitude);
                heatmap.setWeight(weight);
                heatmapList.add(heatmap);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return heatmapList;
    }

    @Override
    public int save(Heatmap article) {
        List<Heatmap> singleArticleInList = Collections.singletonList(article);
        return this.saveAll(singleArticleInList);
    }

    @Override
    public int saveAll(List<Heatmap> articles) {
        try (PreparedStatement statement = conn.prepareStatement("INSERT INTO heatmap(latitude, longitude, weight ) VALUES (?, ?, ?);")) {
            for (Heatmap heatmap: articles) {
                statement.setDouble(1, heatmap.getLongitude());
                statement.setDouble(2, heatmap.getLongitude());
                statement.setInt(3, heatmap.getWeight());
                statement.addBatch();
            }
            return statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
