package com.magspecteur.api.controller;

import com.magspecteur.api.domain.Magazine;
import com.magspecteur.api.domain.MagazineDTO;
import com.magspecteur.api.service.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/api")
public class MagazineController {

	@Autowired
	private MagazineService magazineService;

	@GetMapping("/magazines")
	public ResponseEntity<List<Magazine>> getMagazines(@RequestParam String s) {
		return ResponseEntity.ok()
				.body(magazineService.getAll(s));
	}

	@GetMapping("/magazines/{id}")
	public ResponseEntity<Magazine> getMagazine(Integer id) {
		Magazine magazine = magazineService.getById(id);

		if (magazine == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(magazine);
	}

	@PostMapping("/magazines")
	public ResponseEntity<Magazine> postMagazine(@RequestBody MagazineDTO request) {
		Magazine magazine;
		try {
			magazine = magazineService.create(request);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.created(URI.create("/api/magazines/" + magazine.getId()))
				.body(magazine);
	}

	@PutMapping("/magazines/{id}")
	public ResponseEntity<Magazine> putMagazine(@PathVariable Integer id, @RequestBody Magazine magazine) {
		if (magazineService.getById(id) == null) {
			return ResponseEntity.notFound().build();
		}
		magazineService.update(magazine);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/magazines/{id}")
	public ResponseEntity<Magazine> deleteMagazine(@PathVariable Integer id) {
		if (magazineService.getById(id) == null) {
			return ResponseEntity.notFound().build();
		}
		magazineService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
