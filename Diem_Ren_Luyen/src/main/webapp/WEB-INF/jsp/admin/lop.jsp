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
<form method="post" action="/quanly/lop/them">
<div class="content form-control">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-2">

					<div >
						<label>ID: </label> <label>1${ID}</label>
					</div>
				</div>
				<div class="col-md-8">
					<div class="form-group">
						<label>Tên lớp: </label> <input class="form-control" name="tenlop">
					</div>
				</div>
				<div class="col-md-8">
					<div class="form-group">
						<label>Khoa: </label> <input class="form-control" name="khoa">
					</div>
				</div>
				<div class="col-md-8">
					<div class="form-group">
						<label>Khóa học: </label> <input class="form-control" name="khoahoc">
					</div>
				</div>
				
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="clearfix" style="float: right;">
						<button class="btn btn-primary pull-right" type="submit">Thêm</button>
						<button class="btn btn-primary pull-right" type="button"
							onclick="#">Cập nhật</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>
	<div>
	
		<form class="form-inline my-2 my-lg-0 form-control" action="/quanly/lop/seach" method="get">
			<input class="form-control mr-sm-2" type="search"
				placeholder="Search" aria-label="Search" name=seach>
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit"
				onclick="#">Search</button>

		</form>
	</div>
	<div class="text-center">
	<form action="doiquyen" method="POST">
		<table class="table table-striped">
			<tr>
				<th>ID</th>
				<th>Tên Bộ Câu Hỏi</th>
				<th>Tình trạng</th>

			</tr>
			<c:forEach items="${ListBoCauHoi}" var="bocauhoi">
			<form action="/quanly/bocauhoi/doiquyen" method="post">
				<tr>
				
					<td ><button name="idbocauhoi" value="${bocauhoi.idBoCauHoi}">${bocauhoi.idBoCauHoi}</button></td>
					<td>${bocauhoi.tenBoCauHoi}</td>
					
					<td><button type="submit" 
							style="${bocauhoi.tinhTrang==true ? 'background-color: lightgreen':'background-color: red'}"
							class="btn btn-primary" name="tinhtrang" value="${bocauhoi.tinhTrang}">${bocauhoi.tinhTrang}</button></td>
							
				</tr>
				</form>
			</c:forEach>
		</table>
		</form>
	</div>
</body>
</html>