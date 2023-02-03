<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="layout/header.jsp"%>

<div class="container" style="margin-top: 30px">

	<c:forEach var="item" items="${items}">
		<div class="card">
			<div class="card-body">
				<h4 class="card-title">Card title</h4>
				<p class="card-text">Some example text. Some example text.</p>
				<a href="#" class="card-link">Card link</a> <a href="#"
					class="card-link">Another link</a>
			</div>
		</div>
	</c:forEach>




</div>


<%@ include file="layout/footer.jsp"%>

