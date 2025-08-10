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
			action="${pageContext.request.contextPath}/admin/video-form">
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">Tiêu đề</label>
			  <input value="${title}" name="title" type="text" class="form-control" id="exampleFormControlInput1">
			  <small class="text-danger">${errTitle}</small>
			</div>
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">URL Ảnh</label>
			  <input value="${urlImage}" name="urlImage" type="text" class="form-control" id="exampleFormControlInput1">
			  <small class="text-danger">${errImage}</small>
			</div>
			
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">URL Video</label>
			  <input value="${urlVideo}" name="urlVideo" type="text" class="form-control" id="exampleFormControlInput1">
			  <small class="text-danger">${errVideo}</small>
			</div>
			
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">Mô tả</label>
			  <textarea name="desc" class="form-control" rows="4">${desc}</textarea>
			  <small class="text-danger">${errDesc}</small>
			</div>
			
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">Trạng thái</label>
			  
			  <div class="form-check">
				  <input name="status" value="1" ${status == '1' || status == null ? 'checked' : ''} class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
				  <label class="form-check-label" for="flexRadioDefault1">
				    Hiển thị
				  </label>
				</div>
				<div class="form-check">
				  <input name="status" value="0" ${status == '0' ? 'checked' : ''} class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2">
				  <label class="form-check-label" for="flexRadioDefault2">
				    Ẩn
				  </label>
				</div>
			</div>
			
			<button type="submit" class="btn btn-primary">Thêm video</button>
		</form>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
</body>
</html>