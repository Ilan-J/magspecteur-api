package com.magspecteur.api.service;

import com.magspecteur.api.domain.RegistrationDto;
import com.magspecteur.api.domain.Role;
import com.magspecteur.api.domain.User;
import com.magspecteur.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRepository userRepository;

	public User getByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public User create(RegistrationDto request) {
		List<Role> roles = new ArrayList<>();
		try {
			roles.add(roleService.getByName(Role.ROLE_USER));
		} catch (Exception ignored) {}

		return create(request, roles);
	}

	public User create(RegistrationDto request, List<Role> roles) {
		User user = new User(
				request.email(),
				request.username(),
				passwordEncoder.encode(request.password()),
				roles
		);
		save(user);
		return user;
	}
}
