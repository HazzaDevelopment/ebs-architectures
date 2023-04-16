package org.ebs.shared.server.hibernate.dao;

import org.ebs.shared.server.entity.Graph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphDao extends JpaRepository<Graph, Long> {
    Graph findByType(String type);
}
