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

	@GetMapping("/publishers/{name}")
	public ResponseEntity<Publisher> getPublisher(@PathVariable String name) {
		Publisher publisher = publisherService.getByName(name);

		if (publisher != null) {
			return ResponseEntity.ok().body(publisher);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/publishers")
	public ResponseEntity<Publisher> postPublisher(@RequestBody PublisherDTO request) {
		Publisher publisher;
		try {
			publisher = publisherService.create(request);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
		return ResponseEntity.created(URI.create("/api/publishers/" + publisher.getName()))
				.body(publisher);
	}

	@PutMapping("/publishers/{name}")
	public ResponseEntity<Publisher> putPublisher(@PathVariable String name, @RequestBody PublisherDTO request) {
		if (publisherService.getByName(name) == null) {
			return ResponseEntity.notFound().build();
		}
		publisherService.update(name, request);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/publishers/{name}")
	public ResponseEntity<Publisher> deletePublisher(@PathVariable String name) {
		if (publisherService.getByName(name) == null) {
			return ResponseEntity.notFound().build();
		}
		publisherService.delete(name);
		return ResponseEntity.noContent().build();
	}
}
