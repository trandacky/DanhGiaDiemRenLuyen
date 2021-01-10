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
<form method="post" action="/quanly/taikhoan/update">
<div class="content form-control">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-4">
					<div >
						<label>Mã sinh viên/giảng viên: </label> ${taiKhoan.get().getMaSinhVien()} <input
								name="maSinhVien" value="${taiKhoan.get().getMaSinhVien()}"
								type="hidden">
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label>Mật khẩu </label> <input class="form-control"
								name="matKhau" value="${taiKhoan.get().getMatKhau()}"
								placeholder="${taiKhoan.get().getMatKhau()}"> <input
								name="maSinhVien" value="${taiKhoan.get().getMatKhau()}"
								type="hidden">
							
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label>Ngày sinh</label> <input class="form-control"
							value="${taiKhoan.get().getNgayThangNamSinh()}" type="date" name = "ngaySinh"><input
								name="ngaySinh" value="${taiKhoan.get().getNgayThangNamSinh()}"
								type="hidden">
					</div>
				</div>
				</div>
				<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label>Quyền </label><select class="form-control" name = "quyen" >					
						<option selected value="${taiKhoan.get().getQuyen() }">${taiKhoan.get().getQuyen() }</option>
						<option value="0">Disable</option>
						<option value="1">Admin</option>
						<option value="2">Giảng viên</option>
						<option value="3">Cán bộ lớp</option>
						<option value="4">Sinh viên</option>
						</select>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label> Tên </label><input class="form-control"
								name="tenTaiKhoan" value="${taiKhoan.get().getTen()}"
								placeholder="${taiKhoan.get().getTen()}">
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label>Lớp </label><select class="form-control"
								name="idlop">
								<option value = "${taiKhoan.get().getIdLop().getIdLop()}" selected="selected">${taiKhoan.get().getIdLop().getTenLop()}</option>
								<c:forEach items="${listLop}" var="lop">							
								<option value="${lop.idLop}">${lop.tenLop}</option>
								</c:forEach>
						</select>
								
							</select> 
						
					<%-- <input class="form-control"
								name="idlop" value="${taiKhoan.get().getIdLop().getTenLop()}"
								placeholder="${taiKhoan.get().getIdLop().getTenLop()}"> <input
								name="maSinhVien" value="${taiKhoan.get().getIdLop().getTenLop()}"
								type="hidden">--%>	
					</div>
				</div>
			</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="clearfix" style="float: right;">
						<button class="btn btn-primary pull-right" type="submit"
							onclick="capnhat()">Cập nhật</button>						
					</div>
				</div>
			</div>
		</div>
	
	</form>		
		</table>		
	</div>
</body>
<script>
	function capnhat() {
		alert("Đã cập nhật!");
	}
</script>
</html>