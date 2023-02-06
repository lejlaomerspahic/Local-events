package com.ptf.wp.projekat.dogadjaji_175.services;

import com.ptf.wp.projekat.dogadjaji_175.models.Role;
import com.ptf.wp.projekat.dogadjaji_175.models.User;
import com.ptf.wp.projekat.dogadjaji_175.repository.RoleRepository;
import com.ptf.wp.projekat.dogadjaji_175.repository.UserRepository;
import com.ptf.wp.projekat.dogadjaji_175.viewmodels.UserRegistration;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository repository;
	private final PasswordEncoder encoder;
	private final RoleRepository _roleRepository;
	
	public UserServiceImpl(UserRepository repository,
						   PasswordEncoder encoder, RoleRepository roleRepository) {
		super();
		this.repository = repository;
		this.encoder = encoder;
		_roleRepository = roleRepository;
	}

	@Override
	public User save(@Valid UserRegistration registration) {
		Role role = this._roleRepository.findByName("ROLE_USER");
		if(role == null) {
			role = new Role("ROLE_USER");
			this._roleRepository.save(role);
		}
		User user = new User(
				registration.getFirstName(),
				registration.getLastName(),
				registration.getEmail(),
				encoder.encode(registration.getPassword()),
				role
			);
		
		return repository.save(user);
	}

	@Override
	public boolean userExist(String email) {
		var user = repository.findByEmail(email);
		System.out.println(user);
		
		return user!=null;
	}

}
