package com.magspecteur.api.controller;

import com.magspecteur.api.domain.Article;
import com.magspecteur.api.domain.ArticleDTO;
import com.magspecteur.api.service.ArticleService;
import com.magspecteur.api.service.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/api")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private MagazineService magazineService;

	@GetMapping("/articles")
	public ResponseEntity<List<Article>> getArticles(@RequestParam String s) {
		return ResponseEntity.ok()
				.body(articleService.getAll(s));
	}

	@GetMapping("/articles/{id}")
	public ResponseEntity<Article> getArticle(@PathVariable Integer id) {
		Article article = articleService.getById(id);

		if (article == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(article);
	}

	@PostMapping("/articles")
	public ResponseEntity<Article> postArticle(@RequestBody ArticleDTO request) {
		Article article;
		try {
			article = articleService.create(request);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.created(URI.create("/api/articles/" + article.getId()))
				.body(article);
	}

	@PutMapping("/articles/{id}")
	public ResponseEntity<Article> putArticle(@PathVariable Integer id, @RequestBody Article article) {
		if (articleService.getById(id) == null) {
			return ResponseEntity.notFound().build();
		}
		articleService.update(article);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/articles/{id}")
	public ResponseEntity<Article> deleteArticle(@PathVariable Integer id) {
		if (articleService.getById(id) == null) {
			return ResponseEntity.notFound().build();
		}
		articleService.delete(id);
		return ResponseEntity.noContent().build();
	}

	// Special queries

	@GetMapping("/magazines/{id}/articles")
	public ResponseEntity<List<Article>> getAllByMagazineId(@PathVariable Integer id) {
		if (magazineService.getById(id) == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok()
				.body(articleService.getAllByMagazineId(id));
	}
}
