package com.magspecteur.api.service;

import com.magspecteur.api.domain.Article;
import com.magspecteur.api.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	public List<Article> getAll() {
		return articleRepository.findAll();
	}

	public Article getById(Integer id) {
		Optional<Article> article = articleRepository.findById(id);
		return article.orElse(null);
	}

	public Article getByName(String name) {
		return articleRepository.findByName(name);
	}

	public Article save(Article article) {
		return articleRepository.save(article);
	}
}
