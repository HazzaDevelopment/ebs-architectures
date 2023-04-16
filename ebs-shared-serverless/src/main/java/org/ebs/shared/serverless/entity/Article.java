package org.ebs.shared.serverless.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Article {

    private Long id;
    private String headline;
    private String body;
    private String imagePath;

}
