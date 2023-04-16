package org.ebs.shared.serverless.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class Resource {

    private Long id;
    private String title;
    private String descriptionPanel;
    private String descriptionHeading;

    private String advicePanelHeading;
    private String advicePanel;

    private String listItemsHeading;
    private Map<String, String> listItems;

    public String getUrl(){
        return title.toLowerCase().replaceAll(" ", "_");
    }


}
