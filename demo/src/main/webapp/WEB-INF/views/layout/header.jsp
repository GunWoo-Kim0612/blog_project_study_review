<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>

<!DOCTYPE html>
<html lang="en">
<head>
<title>블로그프로젝트 복습</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
.fakeimg {
	height: 200px;
	background: #aaa;
}
</style>
</head>
<body>
	<h1>principal : ${ principal }</h1>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="/">GWK</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>

		<c:choose>
			<c:when test="${empty principal }">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link"
						href="/auth/loginForm">로그인</a></li>
					<li class="nav-item"><a class="nav-link" href="/auth/joinForm">회원가입</a></li>
				</ul>
			</c:when>

			<c:otherwise>
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link"
						href="/board/saveForm">글쓰기</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/user/updateForm">회원정보</a></li>

					
	
					<c:if test="${principal.user.role ne 'KAKAO_USER'}">
						<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
					</c:if>
					<c:if test="${principal.user.role eq 'KAKAO_USER'}">
						<li class="nav-item"><a class="nav-link" href="/kakaoLogout">카카오로그아웃</a></li>
					</c:if>
				</ul>
			</c:otherwise>
		</c:choose>


	</nav>
	<br>