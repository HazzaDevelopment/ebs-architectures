package org.ebs.shared.serverless.dao;

import org.ebs.shared.serverless.entity.Graph;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphDao extends GenericDao<Graph>{

    public static final String GET_ALL_FROM_GRAPH = "SELECT * FROM graph;";
    public static final String GET_ALL_FROM_LABELS_BY_GRAPH_ID = "SELECT * FROM labels WHERE id = ?;";
    public static final String GET_ALL_FROM_DATA_BY_GRAPH_ID = "SELECT * FROM data WHERE id = ?;";

    public GraphDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Graph> findAll() {
        List<Graph> graphList = new ArrayList<>();
        try {
            PreparedStatement getAllFromGraphStatement = conn.prepareStatement(GET_ALL_FROM_GRAPH);
            PreparedStatement getAllFromLabelsStatement = conn.prepareStatement(GET_ALL_FROM_LABELS_BY_GRAPH_ID);
            PreparedStatement getAllFromDataStatement = conn.prepareStatement(GET_ALL_FROM_DATA_BY_GRAPH_ID);

            ResultSet graphResults = getAllFromGraphStatement.executeQuery();
            while (graphResults.next()) {
                Graph graph = new Graph();

                long id = graphResults.getLong("id");
                String title = graphResults.getString("title");
                String title_label = graphResults.getString("title_label");
                String type = graphResults.getString("type");

                graph.setId(id);
                graph.setTitle(title);
                graph.setTitleLabel(title_label);
                graph.setType(type);

                getAllFromLabelsStatement.setLong(1, id);
                ResultSet labelResults = getAllFromLabelsStatement.executeQuery();
                List<String> labels = new ArrayList<>();
                while (labelResults.next()) {
                    labels.add(labelResults.getString("labels"));
                }
                graph.setLabels(labels);

                getAllFromDataStatement.setLong(1, id);
                ResultSet dataResults = getAllFromDataStatement.executeQuery();
                List<Double> data = new ArrayList<>();
                while (dataResults.next()) {
                    data.add(dataResults.getDouble("data"));
                }
                graphList.add(graph);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return graphList;
    }

    @Override
    public Graph findByType(String type) {
        try {
            PreparedStatement getFromWhereTypeIsStatement = conn.prepareStatement("SELECT * from graph WHERE type = ?;");
            PreparedStatement getAllFromLabelsStatement = conn.prepareStatement(GET_ALL_FROM_LABELS_BY_GRAPH_ID);
            PreparedStatement getAllFromDataStatement = conn.prepareStatement(GET_ALL_FROM_DATA_BY_GRAPH_ID);

            getFromWhereTypeIsStatement.setString(1, type);
            ResultSet graphResults = getFromWhereTypeIsStatement.executeQuery();
            while (graphResults.next()) {
                Graph graph = new Graph();

                long id = graphResults.getLong("id");
                String title = graphResults.getString("title");
                String title_label = graphResults.getString("title_label");
                String typeFromDb = graphResults.getString("type");

                graph.setId(id);
                graph.setTitle(title);
                graph.setTitleLabel(title_label);
                graph.setType(typeFromDb);

                getAllFromLabelsStatement.setLong(1, id);
                ResultSet labelResults = getAllFromLabelsStatement.executeQuery();
                List<String> labels = new ArrayList<>();
                while (labelResults.next()) {
                    labels.add(labelResults.getString("labels"));
                }
                graph.setLabels(labels);

                getAllFromDataStatement.setLong(1, id);
                ResultSet dataResults = getAllFromDataStatement.executeQuery();
                List<Double> data = new ArrayList<>();
                while (dataResults.next()) {
                    data.add(dataResults.getDouble("data"));
                }
                graph.setData(data);
                return graph;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("An error has occured");
    }
    @Override
    public int save(Graph graph) {
        List<Graph> singleGraphInList = Collections.singletonList(graph);
        return this.saveAll(singleGraphInList);
    }

    @Override
    public int saveAll(List<Graph> graphs) {
        try (
                PreparedStatement graphStatement = conn.prepareStatement("INSERT INTO graph(title, title_label, type ) VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
                PreparedStatement labelsStatement = conn.prepareStatement("INSERT INTO labels(id, labels ) VALUES (?, ?);");
                PreparedStatement dataStatement = conn.prepareStatement("INSERT INTO data(id, data ) VALUES (?, ?);");
        ) {
            for (Graph graph: graphs) {
                graphStatement.setString(1, graph.getTitle());
                graphStatement.setString(2, graph.getTitleLabel());
                graphStatement.setString(3, graph.getType());
                graphStatement.executeUpdate();

                ResultSet rs = graphStatement.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    List<String> labels = graph.getLabels();
                    for(String label : labels){
                        labelsStatement.setInt(1, id);
                        labelsStatement.setString(2, label);
                        labelsStatement.executeUpdate();
                    }
                    List<Double> data = graph.getData();
                    for(Double label : data){
                        dataStatement.setInt(1, id);
                        dataStatement.setDouble(2, label);
                        dataStatement.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }
}
