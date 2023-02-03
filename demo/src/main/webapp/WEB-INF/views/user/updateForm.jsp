<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../layout/header.jsp"%>


	<div class="container">
		<h2>사용자 정보</h2>
		<form>
			<!-- form action너무옛날방식  ajax로할것 -->
		<input type="hidden" value="${principal.user.id }" id="id"> 
			<div class="form-group">
				<label for="email">UserName:</label> <input type="text" class="form-control" id="username"   readonly="readonly" value="${principal.user.username }">
			</div>
			<div class="form-group">
				<label for="pwd">Password:</label> <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
			</div>
			<div class="form-group">
				<label for="email">Email:</label> <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" value="${principal.user.email }">
			</div>
		</form>
		<button id="btn-update" class="btn btn-primary">회원수정</button>
	</div>
	<script src="/js/user/user.js"></script>


<%@ include file="../layout/footer.jsp"%>