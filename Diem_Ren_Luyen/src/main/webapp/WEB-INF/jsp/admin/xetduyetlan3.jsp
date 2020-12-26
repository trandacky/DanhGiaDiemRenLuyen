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
	<form method="get" action="/quanly/xetduyetlan3/seach">
		<div class="content form-control">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-7">
						<div class="form-group">
							<label>Lớp: </label> <select name="idbocauhoi"
								class="form-control">
								<option value="">---</option>
								<c:forEach items="${list2}" var="bocauhoi">
									<option value="${bocauhoi.idBoCauHoi}">${bocauhoi.tenBoCauHoi}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<label>Năm học: </label> <select class="form-control"
								name="tinhtrang">
								<%
									for (int i = 2020; i < 2200; i += 1) {
								%>
								<option value="<%=i%>"><%=i%></option>
								<%
									}
								%>
							</select>
						</div>
					</div>
					<div class="col-md-1">
						<div class="form-group">
							<label>Học kỳ: </label> <select name="idbocauhoi"
								class="form-control">
								<option value="1">1</option>
								<option value="2">2</option>
							</select>
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<label>Đã duyệt lần 3: </label> <select name="idbocauhoi"
								class="form-control">
								<option value="false">Chưa duyệt</option>
								<option value="true">Duyệt</option>
							</select>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="clearfix" style="float: right;">
						<button class="btn btn-primary pull-right" type="submit"
							onclick="#">Lọc</button>
					</div>
				</div>
			</div>
		</div>
	</form>
	<div>
		<form class="form-inline my-2 my-lg-0 form-control"
			action="/quanly/cauhoi/seach">
			<input class="form-control mr-sm-2" type="search"
				placeholder="Search" aria-label="Search" name="seach">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit"
				onclick="#">Search</button>

		</form>
	</div>
	<div class="text-center">
		<table class="table table-striped">
			<tr>
				<th>Lớp</th>
				<th>Mã sinh viên</th>
				<th>Tổng điểm lần 2</th>
				<th>Điểm lần 3</th>
				<th>Duyệt lần 3</th>

			</tr>
			<c:forEach items="${ListCauHoi}" var="cauhoi">
				<form method="post" action="/quanly/cauhoi/doiquyen">
					<tr>
						<td><input name="idcauhoi" value="${cauhoi.idCauHoi}"
							type="hidden"><a
							href="/quanly/cauhoi/capnhat/${cauhoi.idCauHoi}">${cauhoi.idCauHoi}</a></td>
						<td><input name="noidungcauhoi"
							value="${cauhoi.noiDungCauHoi}" type="hidden"><a
							href="/quanly/cauhoi/capnhat/${cauhoi.idCauHoi}">${cauhoi.noiDungCauHoi}</a></td>
						<td><input name="diemtoida" value="${cauhoi.diemToiDa}"
							type="hidden">${cauhoi.diemToiDa}</td>
						<td><input name="idbocauhoi"
							value="${cauhoi.idBoCauHoi.getIdBoCauHoi()}" type="hidden">${cauhoi.idBoCauHoi.getIdBoCauHoi()}</td>
						<td><button type="submit" name="tinhtrang"
								style="${cauhoi.tinhTrang==true ? 'background-color: lightgreen':'background-color: red'}"
								class="btn btn-primary" value="${cauhoi.tinhTrang}">${cauhoi.tinhTrang}</button></td>
					</tr>
				</form>
			</c:forEach>
			
		</table>
		<div class="row">
				<div class="col-md-12">
					<div class="clearfix" style="float: right;">
						<button class="btn btn-primary pull-right" type="button"
							onclick="#">Duyệt toàn bộ</button>
					</div>
				</div>
			</div>
	</div>
</body>
</html>