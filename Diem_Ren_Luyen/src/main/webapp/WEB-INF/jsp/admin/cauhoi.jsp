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
				<div class="col-md-2">

					<div >
						<label>ID: </label> <label>1${ID}</label>
					</div>
				</div>
				<div class="col-md-8">
					<div class="form-group">
						<label>Tên bộ câu hỏi: </label> <input class="form-control"
							type="password">
					</div>
				</div>
				<div class="col-md-2">

					<div class="form-group">
						<label>Tình trạng: </label> 
						<select class="form-control">
							<option>True</option>
							<option>False</option>
						</select>
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
				<th>Nội dung</th>
				<th>Điểm tối đa</th>
				<th>Tình trạng</th>

			</tr>
			<c:forEach items="${ListCauHoi}" var="cauhoi">
				<tr>
					<td><a href="" onclick="abc()">${cauhoi.idCauHoi}</a></td>
					<td>${cauhoi.noiDungCauHoi}</td>
					<td>${cauhoi.diemToiDa}</td>
					<td><button type="button"
							style="${cauhoi.tinhTrang==true ? 'background-color: lightgreen':'background-color: red'}"
							class="btn btn-primary">${cauhoi.tinhTrang}</button></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>