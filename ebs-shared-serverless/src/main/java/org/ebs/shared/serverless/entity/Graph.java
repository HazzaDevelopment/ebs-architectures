package org.ebs.shared.serverless.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Graph {

    private Long id;
    private String title;
    private String titleLabel;
    private String type;
    private List<Double> data = new ArrayList<>();
    private List<String> labels = new ArrayList<>();

}
