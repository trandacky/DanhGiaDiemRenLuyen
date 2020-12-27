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
				
				<div class="col-md-6">
					<div class="form-group">
						<label>Tên lớp: </label> <input class="form-control" name="tenlop">
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label>Khoa: </label> <input class="form-control" name="khoa">
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<label>Khóa học: </label> <select class="form-control"
								name="khoahoc">
								<%
									for (int i = 1; i < 100; i += 1) {
								%>
								<option value="<%=i%>"><%=i%></option>
								<%
									}
								%>
							</select>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<label>Tình trạng: </label> 
						<select class="form-control" name="tinhtrang">
							<option value="true">True</option>
							<option value="false">False</option>
						</select>
					</div>
				</div>
				
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="clearfix" style="float: right;">
						<button class="btn btn-primary pull-right" type="submit">Thêm</button>
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
		<table class="table table-striped">
			<tr>
				<th>ID</th>
				<th>Tên Lớp</th>
				<th>Khoa</th>
				<th>Khóa học</th>
				<th>Tình trạng</th>

			</tr>
			<c:forEach items="${ListLop}" var="lop">
			<form method="post" action="/quanly/lop/doiquyen" >
				<tr>
				
					<td ><input name="idlop" value="${lop.idLop}" type="hidden"><a href="/quanly/lop/capnhat/${lop.idLop}">${lop.idLop}</a></td>
					
					<td ><input name="tenlop" value="${lop.tenLop}" type="hidden"><a href="/quanly/lop/capnhat/${lop.idLop}">${lop.tenLop}</a></td>
					<td ><input name="khoa" value="${lop.khoa}" type="hidden"><a href="/quanly/lop/capnhat/${lop.idLop}">${lop.khoa}</a></td>
					<td ><input name="khoahoc" value="${lop.khoaHoc}" type="hidden"><a href="/quanly/lop/capnhat/${lop.idLop}">${lop.khoaHoc}</a></td>
					
					<td><button type="submit" 
							style="${lop.tinhTrang==true ? 'background-color: lightgreen':'background-color: red'}"
							class="btn btn-primary" name="tinhtrang" value="${lop.tinhTrang}">${lop.tinhTrang}</button></td>
							
				</tr>
				</form>
			</c:forEach>
		</table>	
	</div>	
</body>
</html>