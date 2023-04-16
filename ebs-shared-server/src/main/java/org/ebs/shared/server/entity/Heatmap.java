package org.ebs.shared.server.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "heatmap")
public class Heatmap {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private double longitude;
    private double latitude;
    private int weight;

}
