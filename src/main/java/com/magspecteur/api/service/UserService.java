package com.magspecteur.api.service;

import com.magspecteur.api.domain.RegistrationDTO;
import com.magspecteur.api.domain.Role;
import com.magspecteur.api.domain.User;
import com.magspecteur.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRepository userRepository;

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public User getById(Integer id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElse(null);
	}

	public User getByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public User create(RegistrationDTO request, List<Role> roles) {
		User user = new User(
				request.email(),
				request.username(),
				passwordEncoder.encode(request.password()),
				roles
		);
		return save(user);
	}

	public User create(RegistrationDTO request) {
		List<Role> roles = new ArrayList<>();
		try {
			roles.add(roleService.getByName(Role.ROLE_USER));
		} catch (Exception ignored) {}

		return create(request, roles);
	}
}
