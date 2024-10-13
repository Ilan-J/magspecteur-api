package com.magspecteur.api.repository;

import com.magspecteur.api.domain.Article;
import com.magspecteur.api.domain.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
	Article findByName(String name);
	List<Article> findByNameContainingIgnoreCase(String name);
	List<Article> findAllByAuthor(String author);
	List<Article> findAllByMagazineId(Integer id);
	List<Article> findAllByTheme(Theme theme);
}
