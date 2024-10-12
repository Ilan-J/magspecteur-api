package com.magspecteur.api.service;

import com.magspecteur.api.domain.Publisher;
import com.magspecteur.api.domain.PublisherDTO;
import com.magspecteur.api.domain.Theme;
import com.magspecteur.api.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

	@Autowired
	private PublisherRepository publisherRepository;

	public List<Publisher> getAll() {
		return publisherRepository.findAll();
	}

	public Publisher getById(Integer id) {
		Optional<Publisher> publisher = publisherRepository.findById(id);
		return publisher.orElse(null);
	}

	public Publisher getByName(String name) {
		return publisherRepository.findByName(name);
	}

	public Publisher save(Publisher publisher) {
		return publisherRepository.save(publisher);
	}

	public Publisher create(PublisherDTO request, List<Theme> themes) {
		Publisher publisher = new Publisher(
				request.name(),
				request.address(),
				themes
		);
		return save(publisher);
	}

	public Publisher create(PublisherDTO request) {
		List<Theme> themes = new ArrayList<>();
		return create(request, themes);
	}

	public Publisher update(Publisher publisher) {
		return save(publisher);
	}

	public void delete(Integer id) {
		Publisher publisher = getById(id);
		publisherRepository.delete(publisher);
	}
}
