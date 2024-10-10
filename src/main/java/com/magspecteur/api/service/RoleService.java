package com.magspecteur.api.service;

import com.magspecteur.api.domain.Role;
import com.magspecteur.api.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public Role getByName(String name) {
		return roleRepository.findByName(name);
	}

	public Role save(Role role) {
		return roleRepository.save(role);
	}
}
