package com.magspecteur.api.controller;

import com.magspecteur.api.domain.Publisher;
import com.magspecteur.api.domain.PublisherDTO;
import com.magspecteur.api.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PublisherController {

	@Autowired
	private PublisherService publisherService;

	@GetMapping("/publishers")
	public ResponseEntity<List<Publisher>> getPublishers() {
		return ResponseEntity.ok()
				.body(publisherService.getAll());
	}

	@GetMapping("/publishers/{id}")
	public ResponseEntity<Publisher> getPublisher(@PathVariable Integer id) {
		Publisher publisher = publisherService.getById(id);

		if (publisher == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(publisher);
	}

	@PostMapping("/publishers")
	public ResponseEntity<Publisher> postPublisher(@RequestBody PublisherDTO request) {
		Publisher publisher;
		try {
			publisher = publisherService.create(request);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
		return ResponseEntity.created(URI.create("/api/publishers/" + publisher.getId()))
				.body(publisher);
	}

	@PutMapping("/publishers/{id}")
	public ResponseEntity<Publisher> putPublisher(@PathVariable Integer id, @RequestBody PublisherDTO request) {
		if (publisherService.getById(id) == null) {
			return ResponseEntity.notFound().build();
		}
		publisherService.update(id, request);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/publishers/{id}")
	public ResponseEntity<Publisher> deletePublisher(@PathVariable Integer id) {
		if (publisherService.getById(id) == null) {
			return ResponseEntity.notFound().build();
		}
		publisherService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
