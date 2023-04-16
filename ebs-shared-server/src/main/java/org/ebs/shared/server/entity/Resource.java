package org.ebs.shared.server.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Entity
@NoArgsConstructor
@Table(name = "resource")
public class Resource {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    @Column(columnDefinition="TEXT")
    private String descriptionPanel;
    private String descriptionHeading;

    @Column
    private String advicePanelHeading;
    @Column(columnDefinition="TEXT")
    private String advicePanel;

    @Column
    private String listItemsHeading;
    @Column
    @ElementCollection(targetClass = String.class)
    private Map<String, String> listItems;

    public String getUrl(){
        return title.toLowerCase().replaceAll(" ", "_");
    }


}
