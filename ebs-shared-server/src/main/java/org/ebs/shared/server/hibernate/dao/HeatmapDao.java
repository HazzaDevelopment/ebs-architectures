package org.ebs.shared.server.hibernate.dao;

import org.ebs.shared.server.entity.Heatmap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeatmapDao extends JpaRepository<Heatmap, Long> {

}
