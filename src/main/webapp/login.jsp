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
	<div class="col-6 offset-3">
		<form method="POST"
			action="${pageContext.request.contextPath}/login">
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">Email</label>
			  <input value="${email}" name="email" type="text" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com">
			  <small class="text-danger">${errEmail}</small>
			</div>
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">Mật khẩu</label>
			  <input value="${password}" name="password" type="password" class="form-control" id="exampleFormControlInput1" placeholder="**********">
			  <small class="text-danger">${errPassword}</small>
			</div>
			
			<button type="submit" class="btn btn-primary">Đăng nhập</button>
		</form>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
</body>
</html>