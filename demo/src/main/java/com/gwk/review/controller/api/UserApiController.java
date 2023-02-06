package com.gwk.review.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gwk.review.auth.PrincipalDeatils;
import com.gwk.review.model.User;
import com.gwk.review.repository.UserRepository;
import com.gwk.review.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@PostMapping("/auth/joinProc")
	public String joinTest(@RequestBody User user) {
		System.out.println("joinTest함수 실행");
		System.out.println(user);
		
		String rawPassword = user.getPassword();
		String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);
		
		user.setPassword(encodedPassword);
		
		userService.회원가입(user);
		
		return "ajaxTest";
	}
	@GetMapping("/user")
	public String user(Authentication authentication) {
		PrincipalDeatils principal = (PrincipalDeatils) authentication.getPrincipal();
		System.out.println("principal : " + principal.getUser().getId());
		System.out.println("principal : " + principal.getUser().getUsername());
		System.out.println("principal : " + principal.getUser().getPassword());

		return "<h1>user</h1>";
	}
	
}
