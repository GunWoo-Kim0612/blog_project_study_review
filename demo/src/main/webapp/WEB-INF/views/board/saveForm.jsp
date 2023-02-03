<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../layout/header.jsp"%>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>






<div class="container">
	<form>
		<div class="form-group">
			<label for="title">Title:</label> <input type="text" class="form-control" id="title" placeholder="제목입력">
		</div>
		<div class="form-group">
			<label for="content">Content:</label>
			<textarea class="form-control summernote" id="content"></textarea>

		</div>
	</form>
	<button id="btn-write" class="btn btn-primary">출간하기</button>
</div>

<script>
	$('.summernote').summernote({
		placeholder : '본문입력',
		tabsize : 2,
		height : 300
	});
</script>

<script src="/js/board/board.js"></script>

<%@ include file="../layout/footer.jsp"%>