package com.magspecteur.api.repository;

import com.magspecteur.api.domain.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer> {
	Theme findByName(String name);
}
