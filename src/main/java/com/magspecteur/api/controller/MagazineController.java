package com.magspecteur.api.controller;

import com.magspecteur.api.domain.Magazine;
import com.magspecteur.api.service.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api")
public class MagazineController {

	@Autowired
	private MagazineService magazineService;

	@GetMapping("/magazines")
	public ResponseEntity<List<Magazine>> getMagazines() {
		return ResponseEntity.ok()
				.body(magazineService.getAll());
	}

	@GetMapping("/magazines/{id}")
	public ResponseEntity<Magazine> getMagazine(Integer id) {
		Magazine magazine = magazineService.getById(id);

		if (magazine == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(magazine);
	}
}
