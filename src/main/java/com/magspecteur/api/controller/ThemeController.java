package com.magspecteur.api.controller;

import com.magspecteur.api.domain.Theme;
import com.magspecteur.api.domain.ThemeDTO;
import com.magspecteur.api.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

		if (theme == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(theme);
	}

	public ResponseEntity<Theme> postTheme(@RequestBody ThemeDTO request) {
		Theme theme;
		try {
			theme = themeService.create(request);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.created(URI.create("/api/themes/" + theme.getId()))
				.body(theme);
	}

	@PutMapping("/themes/{id}")
	public ResponseEntity<Theme> putTheme(@PathVariable Integer id, @RequestBody Theme theme) {
		if (themeService.getById(id) == null) {
			return ResponseEntity.notFound().build();
		}
		themeService.update(theme);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/themes/{id}")
	public ResponseEntity<Theme> deleteTheme(@PathVariable Integer id) {
		if (themeService.getById(id) == null) {
			return ResponseEntity.notFound().build();
		}
		themeService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
