package com.magspecteur.api.repository;

import com.magspecteur.api.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
	Publisher findByName(String name);
}
