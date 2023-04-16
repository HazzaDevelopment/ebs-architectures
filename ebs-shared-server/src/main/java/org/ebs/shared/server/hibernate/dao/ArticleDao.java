package org.ebs.shared.server.hibernate.dao;

import org.ebs.shared.server.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDao extends JpaRepository<Article, Long> {

}
