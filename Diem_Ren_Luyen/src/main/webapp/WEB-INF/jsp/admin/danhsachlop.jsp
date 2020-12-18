<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<div>
		<form class="form-inline my-2 my-lg-0 form-control">
			<input class="form-control mr-sm-2" type="search"
				placeholder="Search" aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="button"
				onclick="#">Search</button>

		</form>
	</div>
	<div class="text-center">
		<table class="table table-striped">
			<tr>
				<th>ID</th>
				<th>Tên Bộ Câu Hỏi</th>
				<th>Tình trạng</th>

			</tr>
			<c:forEach items="${ListBoCauHoi}" var="bocauhoi">
				<tr>
					<td><a href="" onclick="abc()">${bocauhoi.idBoCauHoi}</a></td>
					<td>${bocauhoi.tenBoCauHoi}</td>
					<td><button type="button"
							style="${bocauhoi.tinhTrang==true ? 'background-color: lightgreen':'background-color: red'}"
							class="btn btn-primary">${bocauhoi.tinhTrang}</button></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>