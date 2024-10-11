package com.magspecteur.api.controller;

import com.magspecteur.api.domain.Theme;
import com.magspecteur.api.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api")
public class ThemeController {

	@Autowired
	private ThemeService themeService;

	@GetMapping("/themes")
	public ResponseEntity<List<Theme>> getThemes() {
		return ResponseEntity.ok()
				.body(themeService.getAll());
	}

	@GetMapping("/themes/{id}")
	public ResponseEntity<Theme> getTheme(@PathVariable Integer id) {
		Theme theme = themeService.getById(id);

		if (theme != null) {
			return ResponseEntity.ok().body(theme);
		}
		return ResponseEntity.notFound().build();
	}
}
