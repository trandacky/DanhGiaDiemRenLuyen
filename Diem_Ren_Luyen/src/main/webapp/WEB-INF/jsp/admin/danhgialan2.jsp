<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="content form-control">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-4">

					<div class="form-group">
						<label>Tên đăng nhập: </label> <input class="form-control">
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label>Password: </label> <input class="form-control"
							type="password">
					</div>
				</div>
				<div class="col-md-4">

					<div class="form-group">
						<label>Họ tên: </label> <input class="form-control">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="clearfix" style="float: right;">
						<button class="btn btn-primary pull-right" type="button"
							onclick="#">Thêm</button>
						<button class="btn btn-primary pull-right" type="button"
							onclick="#">Cập nhật</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<table marigin-left = "30px">
	<tr>
		<td> <th margin ="left">Họ và tên: </th><th></th></td>
		<td> <th>Ngày sinh: </th><th></th></td>
	</tr>
	<tr>
		<td> <th margin ="left">Lớp: </th><th></th></td>
		<td> <th>Khóa: </th><th></th></td>
	</tr>
	<div class="text-center">
	
		<table class="table table-striped">
			<tr>
				<th>Nội dung đánh giá</th> 
				<th>Lần 1</th>
				<th>Lần 2</th>
				<th>Chú thích</th>
			</tr>
			<c:forEach items="${ListBoCauHoi}" var="bocauhoi">
				<tr>
					<td>${bocauhoi.tenCauHoi}</td>
					<td><input type ="text" name = "danhgia1" ></td>
					
					<td></td>
					<td><input type ="text" name = "chuthich" ></td>
				</tr>
			</c:forEach>
		</table>
		<div>
		<div class="row">
				<div class="col-md-12">
					<div class="clearfix" style="float: right;">
						<button class="btn btn-primary pull-right" type="button"
							onclick="#">Hoàn thành</button>
						<button class="btn btn-primary pull-right" type="button"
							onclick="#">Hủy</button>
					</div>
				</div>
			</div>
		</div>
		</table>
		
	</div>
</body>
</html>