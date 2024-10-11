package com.magspecteur.api.service;

import com.magspecteur.api.domain.Magazine;
import com.magspecteur.api.repository.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MagazineService {

	@Autowired
	private MagazineRepository magazineRepository;

	public List<Magazine> getAll() {
		return magazineRepository.findAll();
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
}
