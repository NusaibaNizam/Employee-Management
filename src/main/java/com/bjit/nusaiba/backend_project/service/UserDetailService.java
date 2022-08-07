package com.bjit.nusaiba.backend_project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bjit.nusaiba.backend_project.entity.User;
import com.bjit.nusaiba.backend_project.repository.UserRepository;

@Service
@ComponentScan(value = {"com.bjit.nusaiba.backend_project.repository"})
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> role=repository.findByEmail(email);
		if(role.isEmpty()) {
			throw new UsernameNotFoundException("No user with email: "+email);
		}
		//System.out.println(role);
		return role.get();
	}
	
}
