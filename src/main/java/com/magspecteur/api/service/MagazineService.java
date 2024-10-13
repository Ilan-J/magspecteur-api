package com.magspecteur.api.service;

import com.magspecteur.api.domain.Magazine;
import com.magspecteur.api.domain.MagazineDTO;
import com.magspecteur.api.domain.Publisher;
import com.magspecteur.api.repository.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MagazineService {

	@Autowired
	private MagazineRepository magazineRepository;

	@Autowired
	private PublisherService publisherService;

	public List<Magazine> getAll(String search) {
		if (search == null)
			return magazineRepository.findAll();
		return magazineRepository.findByNameContaining(search);
	}

	public Magazine getById(Integer id) {
		Optional<Magazine> magazine = magazineRepository.findById(id);
		return magazine.orElse(null);
	}

	public Magazine getByName(String name) {
		return magazineRepository.findByName(name);
	}

	public Magazine save(Magazine magazine) {
		return magazineRepository.save(magazine);
	}

	public Magazine create(MagazineDTO request) {
		Publisher publisher = publisherService.getById(request.publisherId());

		Magazine magazine = new Magazine(
				request.name(),
				request.number(),
				request.releaseDate(),
				publisher
		);
		return save(magazine);
	}

	public Magazine update(Magazine magazine) {
		return save(magazine);
	}

	public void delete(Integer id) {
		Magazine magazine = getById(id);
		magazineRepository.delete(magazine);
	}
}
