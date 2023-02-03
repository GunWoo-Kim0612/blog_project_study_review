package com.gwk.review.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gwk.review.model.User;
import com.gwk.review.repository.UserRepository;

@Service
public class PrincipalDetailsService implements UserDetailsService {

	
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("loadByUsername 실행 ");
		
		//username을 받아와 DB에 있는지 확인할것. 해당 메소드필요
		User principal = userRepository.findByUsername(username).orElseThrow(()->{
			return new UsernameNotFoundException("해당 사용자를 찾을수 없습니다." + username);
		});
		System.out.println("UserRepository : 네이밍쿼리 실행 select * from user where username = " + username);
		System.out.println("User 객체 생성, type : " + principal.getClass());
		System.out.println("User 객체를 PrincipalDeatils에 담아 리턴");
		
		//해당 username이 있다면 UserDetails에 담아줌  --> 시큐리티 세션에 유저정보가 저장됨 타입은 UserDetails
		return new PrincipalDeatils(principal);
	}


}