package com.gwk.review.controller.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gwk.review.model.User;
import com.gwk.review.repository.UserRepository;
import com.gwk.review.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/auth/joinProc")
	public String joinTest(@RequestBody User user) {
		System.out.println("joinTest함수 실행");
		System.out.println(user);
		
		userService.회원가입(user);
		
		return "ajaxTest";
	}
}
