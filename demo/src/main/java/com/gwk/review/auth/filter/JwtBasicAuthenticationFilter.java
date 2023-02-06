package com.gwk.review.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gwk.review.auth.PrincipalDeatils;
import com.gwk.review.model.User;
import com.gwk.review.repository.UserRepository;

public class JwtBasicAuthenticationFilter extends BasicAuthenticationFilter {

	private UserRepository userRepository;

	
	public JwtBasicAuthenticationFilter(AuthenticationManager authenticationManager,  UserRepository userRepository) {
		super(authenticationManager);
		this.userRepository = userRepository;

		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("인증 및 권한이 필요한 페이지 요청");
		
		//헤더에서 토큰 가져옴
		String jwtToken = request.getHeader("Authorization");
		System.out.println("jwtToken: "+jwtToken);
		
		//헤더가 없거나 Bearer아 아닌지 확인 후 필터 진행
		if(jwtToken == null || !jwtToken.startsWith("Bearer ")) {
			System.out.println("헤더확인 실패");
			chain.doFilter(request, response);
			return;
		}
		
		//토큰파싱 
		// Bearer뺴고 토큰만가져옴
		String token = request.getHeader("Authorization").replace("Bearer ", "");
		
		//해당 토큰 디코딩해  claim의  username을 추출
		//인코딩시 사용한 HMAC256 에 적용한 sign gwk
		String username = JWT.require(Algorithm.HMAC256("gwk"))
							.build()
							.verify(token).getClaim("username").asString();
		
		System.out.println("토큰에서 추출한 claim [username] : " + username);
		
		if (username != null) {
			User user = userRepository.findByUsername(username).orElseThrow(()->{
				return new UsernameNotFoundException("해당 사용자를 찾을수 없습니다." + username);
			});

			// 인증은 토큰 검증시 끝. 인증을 하기 위해서가 아닌 스프링 시큐리티가 수행해주는 권한 처리를 위해
			// 아래와 같이 토큰을 만들어서 Authentication 객체를 강제로 만들고 그걸 세션에 저장!
			PrincipalDeatils principalDetails = new PrincipalDeatils(user);
			Authentication authentication = new UsernamePasswordAuthenticationToken(
					principalDetails, // 나중에 컨트롤러에서 DI해서 쓸 때 사용하기 편함.
					null, // 패스워드는 모르니까 null 처리, 어차피 지금 인증하는게 아니니까!!
					principalDetails.getAuthorities());

			// 강제로 시큐리티의 세션에 접근하여 값 저장
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		chain.doFilter(request, response);
	}
	
}
