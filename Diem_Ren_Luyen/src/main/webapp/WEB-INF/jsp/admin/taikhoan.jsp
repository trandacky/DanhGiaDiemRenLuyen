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
<form method="post" action="/quanly/taikhoan/them">
<div class="content form-control">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-4">
					<div >
						<label>Mã sinh viên </label><input class="form-control"
							type="text" name ="maSinhVien">
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label>Mật khẩu </label> <input class="form-control"
							type="text" name = "matKhau" >
							
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label>Ngày sinh</label> <input class="form-control"
							type="Date" name = "ngaySinh" placeholder = "yyyy-mm-dd">
					</div>
				</div>
				</div>
				<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label>Quyền </label><select class="form-control" name = "quyen" >					
						<option value="0" selected>Disable</option>
						<option value="1">Admin</option>
						<option value="4">Sinh viên</option>
						<option value="3">Cán bộ lớp</option>
						<option value="2">Giảng viên</option>
						</select>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label> Tên </label> <input class="form-control"
							type="text" name = "tenTaiKhoan">
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label>Lớp </label><select class="form-control" name="idlop">
						<option value="">---</option>
						<c:forEach items="${listLop}" var="lop">
							<option value="${lop.idLop}">${lop.tenLop}</option>
						</c:forEach>
						</select>
					</div>
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
	
	</form>
	<div>
		<form class="form-inline my-2 my-lg-0 form-control" action="/quanly/taikhoan/search" method ="get">
			<input class="form-control mr-sm-2" type="search"
				placeholder="Search" aria-label="Search" name = "search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit"
				onclick="#">Search</button>

		</form>
	</div>
	<div class="text-center">
		<table class="table table-striped">
			<tr>
				<th>Mã sinh viên</th>
				<th>Mật khẩu</th>
				<th>Ngày sinh</th>
				<th>Quyền</th>
				<th>Tên</th>
				<th>Lớp</th>		

			</tr>
			<c:forEach items="${ListTaiKhoan}" var="taikhoan">
			<form method="post" action="/quanly/taikhoan/doiquyen">
				<tr>
					<td ><input name="maSinhVien" value="${taikhoan.maSinhVien}" type="hidden"><a href="/quanly/taikhoan/capnhat/${taikhoan.maSinhVien}">${taikhoan.maSinhVien}</a></td>
					<td ><input name="matKhau" value="${taikhoan.matKhau}" type="hidden"><a href="/quanly/taikhoan/capnhat/${taikhoan.maSinhVien}">${taikhoan.matKhau}</a></td>
					<td ><input name="ngaySinh" value="${taikhoan.ngayThangNamSinh}" type="hidden"><a href="/quanly/taikhoan/capnhat/${taikhoan.maSinhVien}">${taikhoan.ngayThangNamSinh}</a></td>
					<td ><input name="quyen" value="${taikhoan.quyen}" type="hidden"><a href="/quanly/taikhoan/capnhat/${taikhoan.maSinhVien}">${taikhoan.quyen}</a></td>
					<td ><input name="tenTaiKhoan" value="${taikhoan.ten}" type="hidden"><a href="/quanly/taikhoan/capnhat/${taikhoan.maSinhVien}">${taikhoan.ten}</a></td>
					<td ><input name="idlop" value="${taikhoan.idLop}" type="hidden"><a href="/quanly/taikhoan/capnhat/${taikhoan.maSinhVien}">${taikhoan.idLop.tenLop}</a></td>
				</tr>
			</c:forEach>			
		</table>		
	</div>
</body>
</html>