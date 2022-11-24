package com.example.demoauth.service;

import com.example.demoauth.jwt.PersonDetailsImpl;
import com.example.demoauth.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demoauth.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonDetailsServiceImpl implements UserDetailsService {
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isEmpty())
			throw new UsernameNotFoundException("Пользователь не найден");
		return new PersonDetailsImpl(user.get());
	}
}
