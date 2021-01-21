<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
$
.confirm( {
	title: 'Confirm!', content: 'Simple confirm!', confirm: function(){
        alert('Confirmed!');
}

,
cancel: function() {alert ('Canceled!')
	
}
}
)
;
</style>
<body>
	<div class="content form-control">
		<div class="container-fluid">
			<div class="row">

				<div class="col-md-3">

					<div class="form-group">
						<label>Mã sinh viên: </label> <label>${taikhoan.getMaSinhVien()}</label>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Lớp: </label> <label>${taikhoan.getIdLop().getTenLop()}</label>
					</div>
				</div>
				<div class="col-md-3">

					<div class="form-group">
						<label>Họ tên: </label> <label>${taikhoan.getTen()}</label>
					</div>
				</div>
				<div class="col-md-3">

					<div class="form-group">
						<label>Ngày sinh: </label> <label>${taikhoan.getNgayThangNamSinh()}</label>
					</div>
				</div>
				<div class="col-md-3">

					<div class="form-group">
						<label>Khóa: </label> <label>${taikhoan.getIdLop().getKhoaHoc()}</label>
					</div>
				</div>
				<div class="col-md-3">

					<div class="form-group">
						<label>Chức vụ: </label> <label>${taikhoan.getQuyen()}</label>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%int sothutubocauhoi = 1; int sttcauhoi = 1;%>
	<div>
	<form action="/sv/danhgiarenluyen/capnhat" method="POST">
		<table class="table table-striped">
			<tr>
				<th class="text-center">Nội dung đánh giá</th>
				<th class="text-center">Sinh viên tự đánh giá</th>
				<th class="text-center">Chú thích</th>
			</tr>
			<%
				{
				
				int tongdiemlan1=0;
				int tongdiemlan2=0;
				int tongdiemlan3=0;
				
			%>
			<c:forEach items="${listChiTietPhieuRenLuyen}" var="phieu">

				<tr>
					<td><%=sothutubocauhoi%>.
						${phieu.getIdCauHoi().getIdBoCauHoi().getTenBoCauHoi()}</td>
					<%
						sttcauhoi = sothutubocauhoi;
					%>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr class="text-center">

					<td style="width: 650px"><%=sttcauhoi%>)
						${phieu.getIdCauHoi().getNoiDungCauHoi()}: (
						${phieu.getIdCauHoi().getDiemToiDa()}đ )</td>


					<td>
						<input width="50" type="number"
							max="${phieu.getIdCauHoi().getDiemToiDa()}" min="-20"
							class="form-control" name="diemlan1<%=sttcauhoi%>"
							value="${phieu.getDiemLan1()}"
							placeholder="${phieu.getDiemLan1()}"> <input type="hidden" class="form-control" name="idchitietphieurenluyen<%=sttcauhoi%>" value="${phieu.getIdChiTietPhieuRenLuyen()}">
					</td>
					
					<td style="width: 300px"><input class="form-control" name="ghichu<%=sttcauhoi%>" value="${phieu.getGhiChu()}" placeholder="${phieu.getGhiChu()}"></td>
				</tr>
				<%
					sttcauhoi++;
					sothutubocauhoi++;
				%>
			</c:forEach>
			<%
				}
			%>
			<input name="sttcauhoi" value="<%=sttcauhoi%>" type="hidden">
			<tr class="text-center">
			<td style="float:right">Tổng điểm:</td>
			<td><input width="50" class="form-control" name="tongdiemlan1" value="${phieurenluyen.getTongDiemLan1()}" disabled></td>
			<td></td>
			</tr>
		</table>
		
		<input type="hidden" name="idphieu" value="${phieurenluyen.getIdPhieuRenLuyen()}">
		<div class="clearfix" style="float: right;">
						<button class="btn btn-primary pull-right" type="submit" name="capnhat" value="capnhat" onclick="demo()">Cập nhật</button>
						
						
						
		</div>
		</form>
	</div>
</body>
<script>
	function demo() {
		alert("Cập nhật thành công!");
	}
</script>
</html>