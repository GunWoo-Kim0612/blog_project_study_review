package com.gwk.review.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gwk.review.model.User;

import lombok.Data;

@Data
public class PrincipalDeatils implements UserDetails {

	private User user;	//DB에 저장하는 User 객체도 포함되어 있어야 하기 때문에 컴포지션 한다.(객체를 품고있는것)
	
	
	//User 객체를 가져오고
	public PrincipalDeatils(User user) {
		System.out.println("User전달받아  PrincipalDedails객체 생성" );
		this.user = user;
	}
	
	//권한 일단은 null;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	//계정이 만료되지 않았는지? true : 만료안됨
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	//계정이 잠겨있지 않은지? true : 잠기지않음
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	//비밀번호가 만료되지 않았는지? true : 만료되지않음
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	// 계정이 활정화(사용가능)인지 리턴한다. (true : 활성화)
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
