package com.magspecteur.api.service;

import com.magspecteur.api.domain.Theme;
import com.magspecteur.api.domain.ThemeDTO;
import com.magspecteur.api.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {

	@Autowired
	private ThemeRepository themeRepository;

	public List<Theme> getAll() {
		return themeRepository.findAll();
	}

	public Theme getById(Integer id) {
		Optional<Theme> theme = themeRepository.findById(id);
		return theme.orElse(null);
	}

	public Theme getByName(String name) {
		return themeRepository.findByName(name);
	}

	public Theme save(Theme theme) {
		return themeRepository.save(theme);
	}

	public Theme create(ThemeDTO request) {
		Theme theme = new Theme(
				request.name()
		);
		return save(theme);
	}

	public Theme update(Theme theme) {
		return save(theme);
	}

	public void delete(Integer id) {
		Theme theme = getById(id);
		themeRepository.delete(theme);
	}
}
