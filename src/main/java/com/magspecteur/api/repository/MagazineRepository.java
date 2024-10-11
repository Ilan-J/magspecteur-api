package com.magspecteur.api.repository;

import com.magspecteur.api.domain.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Integer> {
	Magazine findByName(String name);
}
