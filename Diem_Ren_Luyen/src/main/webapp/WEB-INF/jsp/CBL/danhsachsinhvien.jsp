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
				<th>Tổng điểm lần 1</th>		
				<th>Tổng điểm lần 2</th>	

			</tr>
			<c:forEach items="${listPhieuRenLuyen}" var="phieu">
				<form action="#" method="post">
					<tr>						
						<td><input type="hidden" name="idphieu"
							value="${phieu.getIdPhieuRenLuyen()}"><a
							href="/cbl/danhsachsinhvien/${phieu.getIdPhieuRenLuyen()}">${phieu.getMaSinhVien().getMaSinhVien()}</a></td>
						<td>${phieu.getMaSinhVien().getTen()}</td>
						<td>${phieu.getMaSinhVien().getNgayThangNamSinh()}</td>
						<td>${phieu.getTongDiemLan1()}</td>
						<td>${phieu.getTongDiemLan2()}</td>

					
					</tr>
				</form>
			</c:forEach>

		</table>		
	</div>
</body>
</html>