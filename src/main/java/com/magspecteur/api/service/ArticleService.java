package com.magspecteur.api.service;

import com.magspecteur.api.domain.Article;
import com.magspecteur.api.domain.ArticleDTO;
import com.magspecteur.api.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	public List<Article> getAll(String search) {
		if (search == null)
			return articleRepository.findAll();
		return articleRepository.findByNameContainingIgnoreCase(search);
	}

	public List<Article> getAllByMagazineId(Integer id) {
		return articleRepository.findAllByMagazineId(id);
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

	public Article create(ArticleDTO request) {
		Article article = new Article(
				request.name(),
				request.author(),
				request.magazineId(),
				request.theme()
		);
		return save(article);
	}

	public Article update(Article article) {
		return save(article);
	}

	public void delete(Integer id) {
		Article article = getById(id);
		articleRepository.delete(article);
	}
}
