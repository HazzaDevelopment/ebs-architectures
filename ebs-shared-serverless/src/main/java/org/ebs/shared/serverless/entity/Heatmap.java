package org.ebs.shared.serverless.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Heatmap {

    private Long id;
    private double longitude;
    private double latitude;
    private int weight;

}
