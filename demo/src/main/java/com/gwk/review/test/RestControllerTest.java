package com.gwk.review.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestControllerTest {
	
	
	
	
	@GetMapping("/manager")
	public String manager() {
		System.out.println("manager");
		return "manager";
	}
	
	
}
