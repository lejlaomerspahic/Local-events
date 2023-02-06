package com.ptf.wp.projekat.dogadjaji_175.services;

import com.ptf.wp.projekat.dogadjaji_175.models.User;
import com.ptf.wp.projekat.dogadjaji_175.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	private final UserRepository userRepository;
	
	public UserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByEmail(username);
		if((user == null) || (user.getAktivan()==false)) throw new UsernameNotFoundException
				("Nije pronađen korisnik ili je suspendovan");
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
	}


}
