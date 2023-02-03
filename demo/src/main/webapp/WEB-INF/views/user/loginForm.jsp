<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../layout/header.jsp"%>


<div class="container">
	<h2>로그인</h2>
	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">UserName:</label> <input type="text"
				class="form-control" id="username" placeholder="Enter username"
				name="username">
		</div>
		<div class="form-group">
			<label for="pwd">Password:</label> <input type="password"
				class="form-control" id="password" placeholder="Enter password"
				name="password">
		</div>
		<button id="btn-login" class="btn btn-primary">로그인</button>
		<a
			href="https://kauth.kakao.com/oauth/authorize?client_id=8e850b343748a7c248cff773f99c2b16&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code
			"><img
			src="/image/kakao_login.png" height="38px" width="74px"></img> </a>
	</form>
</div>



<%@ include file="../layout/footer.jsp"%>