package org.ebs.shared.serverless.dao;

import org.ebs.shared.serverless.entity.Resource;

import java.sql.*;
import java.util.*;

public class ResourceDao extends GenericDao<Resource>{

    public ResourceDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Resource> findAll() {
        List<Resource> resourceList = new ArrayList<>();
        try {
            String findAllResourcesQuery = "SELECT * FROM resource;";
            String findAllResourceListItemsQuery = "SELECT * FROM resource_list_items WHERE resource_id = ?;";

            PreparedStatement findAllResourceStmt = conn.prepareStatement(findAllResourcesQuery);
            PreparedStatement findAllResourceListItemsStmt = conn.prepareStatement(findAllResourceListItemsQuery);

            ResultSet findAllResources = findAllResourceStmt.executeQuery();
            while (findAllResources.next()) {
                long resource_id = findAllResources.getLong("id");
                String advicePanel = findAllResources.getString("advice_panel");
                String advicePanelHeading = findAllResources.getString("advice_panel_heading");
                String descriptionHeading = findAllResources.getString("description_heading");
                String descriptionPanel = findAllResources.getString("description_panel");
                String listItemsHeading = findAllResources.getString("list_items_heading");
                String title = findAllResources.getString("title");

                Resource resource = new Resource();
                resource.setId(resource_id);
                resource.setAdvicePanel(advicePanel);
                resource.setAdvicePanelHeading(advicePanelHeading);
                resource.setDescriptionHeading(descriptionHeading);
                resource.setDescriptionPanel(descriptionPanel);
                resource.setListItemsHeading(listItemsHeading);
                resource.setTitle(title);


                findAllResourceListItemsStmt.setLong(1, resource_id);
                ResultSet listItems = findAllResourceListItemsStmt.executeQuery();
                Map<String, String> listItemsMap = new HashMap<>();
                while (listItems.next()) {
                    listItemsMap.put(
                            listItems.getString("list_items_key"),
                            listItems.getString("list_items"));
                }
                resource.setListItems(listItemsMap);
                resourceList.add(resource);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resourceList;
    }

    @Override
    public Resource findByType(String type) {
        try {
            String regQuery = "SELECT * from graph WHERE type = '?';";
            String findAllResourceListItemsQuery = "SELECT * FROM resource_list_items WHERE resource_id = ?;";

            PreparedStatement pst = conn.prepareStatement(regQuery);
            PreparedStatement findAllResourceListItemsStmt = conn.prepareStatement(findAllResourceListItemsQuery);

            pst.setString(1, type);

            Resource resource = new Resource();
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Long id = rs.getLong("id");
                String advicePanel = rs.getString("advice_panel");
                String advicePanelHeading = rs.getString("advice_panel_heading");
                String descriptionHeading = rs.getString("description_heading");
                String descriptionPanel = rs.getString("description_panel");
                String listItemsHeading = rs.getString("list_items_heading");
                String title = rs.getString("title");

                resource.setId(id);
                resource.setAdvicePanel(advicePanel);
                resource.setAdvicePanelHeading(advicePanelHeading);
                resource.setDescriptionHeading(descriptionHeading);
                resource.setDescriptionPanel(descriptionPanel);
                resource.setListItemsHeading(listItemsHeading);
                resource.setTitle(title);

                findAllResourceListItemsStmt.setLong(1, id);
                ResultSet listItems = findAllResourceListItemsStmt.executeQuery();
                Map<String, String> listItemsMap = new HashMap<>();
                while (listItems.next()) {
                    listItemsMap.put(
                            listItems.getString("list_items_key"),
                            listItems.getString("list_items"));
                }
                resource.setListItems(listItemsMap);
                return resource;
            }
            else {
                throw new RuntimeException(String.format("Graph for %s either returned no results or returned more than 1 result", type));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int save(Resource graph) {
        List<Resource> singleGraphInList = Collections.singletonList(graph);
        return this.saveAll(singleGraphInList);
    }

    @Override
    public int saveAll(List<Resource> resources) {
        try (
                PreparedStatement resourceStatement = conn.prepareStatement("INSERT INTO resource(advice_panel, advice_panel_heading, description_heading, description_panel, list_items_heading, title ) VALUES (?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
                PreparedStatement resourceListItems = conn.prepareStatement("INSERT INTO resource_list_items(resource_id, list_items, list_items_key ) VALUES (?, ?, ?);");
        ) {
            for (Resource resource: resources) {

                resourceStatement.setString(1, resource.getAdvicePanel());
                resourceStatement.setString(2, resource.getAdvicePanelHeading());
                resourceStatement.setString(3, resource.getDescriptionHeading());
                resourceStatement.setString(4, resource.getDescriptionPanel());
                resourceStatement.setString(5, resource.getListItemsHeading());
                resourceStatement.setString(6, resource.getTitle());

                resourceStatement.executeUpdate();

                ResultSet rs = resourceStatement.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    Map<String, String> listItems = resource.getListItems();

                    for (String key : listItems.keySet()) {
                        resourceListItems.setInt(1, id);
                        resourceListItems.setString(2, listItems.get(key));
                        resourceListItems.setString(3, key);
                        resourceListItems.executeUpdate();
                    }
                }

                }
            }
         catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }
}
