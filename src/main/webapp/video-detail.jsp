<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
</head>
<body>
	<h1>${video.title}</h1>
	<h1>${video.views}</h1>
	<h1>Trạng thái yêu thích: ${favorite == null ? 'Không' : 'Có'}</h1>
	<form method="POST"
		action="${pageContext.request.contextPath}/favorites">
		<input type="hidden" name="video_id" value="${video.id}"/>
		<button class="btn btn-primary" type="submit">Yêu thích</button>
	</form>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
</body>
</html>