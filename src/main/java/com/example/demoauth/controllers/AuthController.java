package com.example.demoauth.controllers;

import java.util.*;
import java.util.stream.Collectors;

import com.example.demoauth.models.User;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.demoauth.jwt.JwtUtil;
import com.example.demoauth.models.ERole;
import com.example.demoauth.models.Role;
import com.example.demoauth.pojo.res.JwtResponse;
import com.example.demoauth.pojo.req.LoginRequest;
import com.example.demoauth.pojo.res.MessageResponse;
import com.example.demoauth.pojo.req.SignupRequest;
import com.example.demoauth.repository.RoleRepository;
import com.example.demoauth.repository.UserRepository;
import com.example.demoauth.jwt.PersonDetailsImpl;

@RestController
@Data
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 180)
public class AuthController {
	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
							loginRequest.getUsername(),
							loginRequest.getPassword()));
		} catch (BadCredentialsException e){
			return ResponseEntity.badRequest().body("incorrect credentials");
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		PersonDetailsImpl personDetails = (PersonDetailsImpl) authentication.getPrincipal();
		List<String> roles = personDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		String jwt = jwtUtil.generateToken(personDetails.getUsername(), roles);
		System.out.println(jwt);
		return ResponseEntity.ok(new JwtResponse(jwt, personDetails.getUsername(), personDetails.getEmail()));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
		
		if (userRepository.existsByUsername(signupRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is exist"));
		}
		
		if (userRepository.existsByEmail(signupRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is exist"));
		}
		
		User user = new User(signupRequest.getUsername(),
				signupRequest.getEmail(),
				passwordEncoder.encode(signupRequest.getPassword()));
		
		Set<String> reqRoles = signupRequest.getRoles();
		Set<Role> roles = new HashSet<>();
		
		if (reqRoles == null) {
			Role userRole = roleRepository
					.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
			roles.add(userRole);
		} else {
			reqRoles.forEach(r -> {
				if ("admin".equals(r)) {
					Role adminRole = roleRepository
							.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error, Role ADMIN is not found"));
					roles.add(adminRole);
				} else {
					Role userRole = roleRepository
							.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User CREATED"));
	}

	private User userDetails(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		PersonDetailsImpl principal = (PersonDetailsImpl) authentication.getPrincipal();
		return principal.getUser();
	}
}
