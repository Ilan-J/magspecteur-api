package com.magspecteur.api.repository;

import com.magspecteur.api.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
	Article findByName(String name);
}
