<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="/quanly/cauhoi/them">
<div class="content form-control">
		<div class="container-fluid">
		<div class = "row">
				<div class="col-md-12">
					<div class="form-group">
						<label>Nội dung câu hỏi: </label> <input name="noidungcauhoi" class="form-control">
					</div>
				</div>
				<div class="col-md-8">

					<div class="form-group">
						<label>Bộ câu hỏi: </label> 
						<select name="idbocauhoi" class="form-control">
						<option value="">---</option>
						<c:forEach items="${list2}" var="bocauhoi">
							<option value="${bocauhoi.tenBoCauHoi}">${bocauhoi.tenBoCauHoi}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="col-md-2">

					<div  class="form-group">
						<label>Tình trạng: </label> 
						<select class="form-control" name="tinhtrang" >
							<option value="true">True</option>
							<option value="false">False</option>
						</select>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<label>Điểm tối đa: </label> <input name="diemtoida" class="form-control">
					</div>
				</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="clearfix" style="float: right;">
						<button class="btn btn-primary pull-right" type="submit"
							onclick="#">Thêm</button>
					</div>
				</div>
			</div>
		</div>
	</form>
	<div>
		<form class="form-inline my-2 my-lg-0 form-control" action="/quanly/cauhoi/seach">
			<input class="form-control mr-sm-2" type="search"
				placeholder="Search" aria-label="Search" name="seach">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit"
				onclick="#">Search</button>

		</form>
	</div>
	<div class="text-center">
		<table class="table table-striped">
			<tr>
				<th>ID</th>
				<th>Nội dung</th>
				<th>Điểm tối đa</th>
				<th>Bộ câu hỏi</th>
				<th>Tình trạng</th>

			</tr>
			<c:forEach items="${ListCauHoi}" var="cauhoi">
			<form method="post" action="/quanly/cauhoi/doiquyen" >
				<tr>
					<td><input name="idcauhoi" value="${cauhoi.idCauHoi}" type="hidden"><a href="/quanly/cauhoi/capnhat/${cauhoi.idCauHoi}">${cauhoi.idCauHoi}</a></td>
					<td><input name="noidungcauhoi" value="${cauhoi.noiDungCauHoi}" type="hidden"><a href="/quanly/cauhoi/capnhat/${cauhoi.idCauHoi}">${cauhoi.noiDungCauHoi}</a></td>
					<td><input name="diemtoida" value="${cauhoi.diemToiDa}" type="hidden">${cauhoi.diemToiDa}</td>
					<td><input name="idbocauhoi" value="${cauhoi.idBoCauHoi.getIdBoCauHoi()}" type="hidden">${cauhoi.idBoCauHoi.getIdBoCauHoi()}</td>
					<td><button type="submit" name="tinhtrang"
							style="${cauhoi.tinhTrang==true ? 'background-color: lightgreen':'background-color: red'}"
							class="btn btn-primary" value="${cauhoi.tinhTrang}">${cauhoi.tinhTrang}</button></td>
				</tr>
			</form>
			</c:forEach>
		</table>
	</div>
</body>
</html>