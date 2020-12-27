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
	<form method="post" action="/quanly/lop/update">
		<div class="content form-control">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-1">
						<div class="form-group">
							<label>ID: </label> ${lop.get().getIdLop()} <input
								name="idlop" value="${lop.get().getIdLop()}"
								type="hidden"> <input name="tinhtrang"
								value="${lop.get().getTinhTrang()}" type="hidden">
						</div>
					</div>
				</div>
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group">
							<label>Tên lớp: </label> <input class="form-control"
								name="tenlop" value="${lop.get().getTenLop()}"
								placeholder="${lop.get().getTenLop()}"> <input
								name="idlop" value="${lop.get().getTenLop()}"
								type="hidden">
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label>Khoa: </label> <input class="form-control"
								name="khoa" value="${lop.get().getKhoa()}"
								placeholder="${lop.get().getKhoa()}"> 
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<%--<label>Khóa học: </label> <input class="form-control"
								name="khoahoc" value="${lop.get().getKhoaHoc()}"
								placeholder="${lop.get().getKhoaHoc()}">--%>
								
							<label>Khóa học: </label> <select class="form-control"
								name="khoahoc">
								<option selected="selected">${lop.get().getKhoaHoc()}</option>
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
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="clearfix" style="float: right;">
							<button class="btn btn-primary pull-right" onclick="demo()"
								type="submit">Cập nhật</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<form method="post" action="/quanly/lop/capnhat/up">
		<div class="content form-control">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-7">
						<div class="form-group">
							<label>Sinh viên: </label> 
							<select name = "layidtaikhoan" class="form-control">
								<c:forEach items="${listTaiKhoan}" var="taikhoan">
									<option value="${taikhoan.maSinhVien}">${taikhoan.ten}</option>
								</c:forEach>
							</select>

						</div>
					</div>
				</div>
				<input name="idlop" value="${lop.get().getIdLop()}"
					type="hidden">
				<div class="row">
					<div class="col-md-12">
						<div class="clearfix" style="float: right;">
							<button class="btn btn-primary pull-right" onclick="demo()"
								type="submit">Thêm vào lớp</button>

						</div>

					</div>
				</div>
			</div>
		</div>
	</form>
	<div class="text-center">
		<table class="table table-striped">
			<tr>
				<th>ID</th>
				<th>Mật khẩu</th>
				<th>Ngày tháng năm sinh</th>
				<th>Tên</th>

			</tr>
			<c:forEach items="${ListTaiKhoan}" var="taikhoan">
				<form method="post" action="/quanly/lop/doiquyen">
					<tr>
						<td>${taikhoan.maSinhVien}</td>
						<td>${taikhoan.matKhau}</td>
						<td>${taikhoan.ngayThangNamSinh}</td>
						<td>${taikhoan.ten}</td>
						

					</tr>
				</form>
			</c:forEach>
		</table>
	</div>
</body>
<script>
	function demo() {
		alert("Đã cập nhật!");
	}
</script>
</html>