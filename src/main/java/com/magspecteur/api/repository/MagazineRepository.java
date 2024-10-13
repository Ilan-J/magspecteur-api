package com.magspecteur.api.repository;

import com.magspecteur.api.domain.Magazine;
import com.magspecteur.api.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Integer> {
	Magazine findByName(String name);
	List<Magazine> findByNameContainingIgnoreCase(String name);
	List<Magazine> findAllByPublisher(Publisher publisher);
}
