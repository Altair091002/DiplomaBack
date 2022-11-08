package com.example.demoauth.service;

import com.example.demoauth.jwt.PersonDetailsImpl;
import com.example.demoauth.models.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demoauth.repository.PersonRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class PersonDetailsServiceImpl implements UserDetailsService {
	private final PersonRepository personRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Person> person = personRepository.findByUsername(username);
		if (person.isEmpty())
			throw new UsernameNotFoundException("Пользователь не найден");


		return new PersonDetailsImpl(person.get());
	}

}
