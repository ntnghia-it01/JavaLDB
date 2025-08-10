<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
</head>
<body>
	<div class="container pt-3">
		<a href="${pageContext.request.contextPath}/admin/video-form" class="btn btn-primary mb-3">Thêm video</a>
		
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">ID</th>
		      <th scope="col">Tiêu đề</th>
		      <th scope="col">Mô tả</th>
		      <th scope="col">Ảnh</th>
		      <th scope="col">Url</th>
		      <th scope="col">Lượt xem</th>
		      <th scope="col">Lượt thích</th>
		      <th scope="col">Lượt chia sẽ</th>
		      <th scope="col">Hành động</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<c:forEach items="${videos}" var="item">
		  		<tr>
			      <td>${item.id}</td>
			      <td>${item.title}</td>
			      <td>${item.desc}</td>
			      <td>${item.poster}</td>
			      <td>${item.url}</td>
			      <td>${item.views}</td>
			      <td>${item.favorites.size()}</td>
			      <td>${item.shares.size()}</td>
			      <td>
			      	<form method="POST"
			      		action="${pageContext.request.contextPath}/admin/video-delete">
			      		<input type="hidden" name="id" value="${item.id}"/>
			      		<button class="btn btn-danger">Xoá</button>
			      	</form>
			      	
			      	<a class="btn btn-warning" href="${pageContext.request.contextPath}/admin/video-form?video_id=${item.id}">Sửa</a>
			      </td>
			    </tr>
		  	</c:forEach>
		  </tbody>
		</table>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
</body>
</html>