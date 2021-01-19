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
		<form class="form-inline my-2 my-lg-0 form-control" action="#" method ="get">
			<input class="form-control mr-sm-2" type="search"
				placeholder="Search" aria-label="Search" name = "search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit"
				onclick="#">Search</button>
		</form>
	<div class="text-center">
		<table class="table table-striped">
			<tr>
				<th>Mã sinh viên</th>
				<th>Tên</th>
				<th>Ngày sinh</th>		

			</tr>
			<c:forEach items="${ListTaiKhoan}" var="taikhoan">
			<form method="post" action="/cbl/danhsachsinhvien/capnhat/up">
				<tr>
					<td ><input name="maSinhVien" value="${taikhoan.maSinhVien}" type="hidden"><a href="/quanly/taikhoan/capnhat/${taikhoan.maSinhVien}">${taikhoan.maSinhVien}</a></td>
					
					<td ><input name="tenTaiKhoan" value="${taikhoan.ten}" type="hidden"><a href="/quanly/taikhoan/capnhat/${taikhoan.maSinhVien}">${taikhoan.ten}</a></td>	
					<td ><input name="ngaySinh" value="${taikhoan.ngayThangNamSinh}" type="hidden"><a href="/quanly/taikhoan/capnhat/${taikhoan.maSinhVien}">${taikhoan.ngayThangNamSinh}</a></td>
					</tr>
			</c:forEach>			
		</table>		
	</div>
</body>
</html>