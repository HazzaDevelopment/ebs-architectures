package org.ebs.shared.serverless.dao;


import org.ebs.shared.serverless.entity.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArticleDao extends GenericDao<Article>{

    public ArticleDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Article> findAll() {
        List<Article> articleList = new ArrayList<>();
        try(
                PreparedStatement pst = conn.prepareStatement("SELECT * from article;");
        ) {
            String regQuery = "";

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String headline = rs.getString("headline");
                String body = rs.getString("body");
                String imagePath = rs.getString("image_path");

                Article article = new Article();
                article.setId(id);
                article.setHeadline(headline);
                article.setBody(body);
                article.setImagePath(imagePath);
                articleList.add(article);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articleList;
    }

    @Override
    public int save(Article article) {
        List<Article> singleArticleInList = Collections.singletonList(article);
        return this.saveAll(singleArticleInList);
    }

    @Override
    public int saveAll(List<Article> articles) {
        try (PreparedStatement statement = conn.prepareStatement("INSERT INTO article(headline, body, image_path ) VALUES (?, ?, ?);")) {
            for (Article article: articles) {
                statement.setString(1, article.getHeadline());
                statement.setString(2, article.getBody());
                statement.setString(3, article.getImagePath());
                System.out.println("Adding SQL: " + statement);
                statement.addBatch();
            }
            return statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
