package org.ebs.shared.server.hibernate.dao;

import org.ebs.shared.server.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceDao extends JpaRepository<Resource, Long> {
}


