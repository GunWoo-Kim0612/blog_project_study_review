package com.gwk.review.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwk.review.model.User;
import com.gwk.review.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void 회원가입(User user) {
		
		System.out.println("save함수 실행");
		userRepository.save(user);
	}
}
