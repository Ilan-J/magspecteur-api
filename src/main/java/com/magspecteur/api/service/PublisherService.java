package com.magspecteur.api.service;

import com.magspecteur.api.domain.Publisher;
import com.magspecteur.api.domain.PublisherDTO;
import com.magspecteur.api.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

	@Autowired
	private PublisherRepository publisherRepository;

	public Publisher getByName(String name) {
		return publisherRepository.findByName(name);
	}

	public List<Publisher> getAll() {
		return publisherRepository.findAll();
	}

	public Publisher save(Publisher publisher) {
		return publisherRepository.save(publisher);
	}

	public Publisher create(PublisherDTO request) {
		Publisher publisher = new Publisher(
				request.name(),
				request.address(),
				request.themes()
		);
		save(publisher);
		return publisher;
	}

	public Publisher update(String name, PublisherDTO request) {
		Publisher publisher = publisherRepository.findByName(name);
		publisher.setName(request.name());
		publisher.setAddress(request.address());
		publisher.setThemes(request.themes());
		save(publisher);
		return publisher;
	}

	public void delete(String name) {
		Publisher publisher = publisherRepository.findByName(name);
		publisherRepository.delete(publisher);
	}
}
