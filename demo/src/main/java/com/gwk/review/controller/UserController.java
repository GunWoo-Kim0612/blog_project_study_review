package com.gwk.review.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.gwk.review.auth.PrincipalDeatils;

@Controller
public class UserController {
	
	@GetMapping("/")
	public String home(@AuthenticationPrincipal PrincipalDeatils principal) {
		System.out.println("메인페이지이동");
		return "index";
	}
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		System.out.println("joinForm 이동");
		return "/user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		System.out.println("joinForm 이동");
		return "/user/loginForm";
	}
}