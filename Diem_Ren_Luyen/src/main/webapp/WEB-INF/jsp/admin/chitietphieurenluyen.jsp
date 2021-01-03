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
	<%int sothutubocauhoi = 1; %>
	<div>
	<form action="/quanly/duyetlan3/tinhtongvaduyetphieu" method="POST">
		<table class="table table-striped">
			<tr>
				<th class="text-center">Nội dung đánh giá</th>
				<th class="text-center">Sinh viên tự đánh giá</th>
				<th class="text-center">Tập thể lớp đánh giá</th>
				<th class="text-center">Khoa đánh giá</th>
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
						int sttcauhoi = sothutubocauhoi;
					%>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr class="text-center">

					<td style="width: 700px"><%=sttcauhoi%>)
						${phieu.getIdCauHoi().getNoiDungCauHoi()}: (
						${phieu.getIdCauHoi().getDiemToiDa()}đ )</td>

					<td>${phieu.getDiemLan1()}</td>
					<td>${phieu.getDiemLan2()}</td>
					<td>
						<input class="form-control" name="diemlan3<%=sttcauhoi%>" value="${phieu.getDiemLan3()}" placeholder="${phieu.getDiemLan3()}">
						<input type="hidden" class="form-control" name="idcauhoi<%=sttcauhoi%>" value="${phieu.getIdCauHoi().getIdCauHoi()}">
					</td>
					<td style="width: 200px">${phieu.getGhiChu()}</td>
				</tr>
				<%
					sttcauhoi++;
					sothutubocauhoi++;
				%><input name="sttcauhoi" value="<%=sttcauhoi%>" type="hidden">
			</c:forEach>
			<%
				}
			%>
			<tr class="text-center">
			<td style="float:right">Tổng điểm:</td>
			<td>${listChiTietPhieuRenLuyen.get(0).getIdPhieuRenLuyen().getTongDiemLan1()}</td>
			<td>${listChiTietPhieuRenLuyen.get(0).getIdPhieuRenLuyen().getTongDiemLan2()}</td>
			<td><input class="form-control" name="tongdiemlan3" disabled></td>
			<td></td>
			</tr>
		</table>
		
		<input type="hidden" name="idphieu" value="${listChiTietPhieuRenLuyen.get(0).getIdPhieuRenLuyen().getIdPhieuRenLuyen()}">
		<div class="clearfix" style="float: right;">
						<button class="btn btn-primary pull-right" type="submit" name="tinhtong" value="tinhtong">Tính tổng</button>
						<a href="/quanly/duyetlan3">
						
						<button class="btn btn-primary pull-right" type="button">Hủy và Thoát</button></a>
						<button class="btn btn-primary pull-right" onclick="return confirm('Bạn muốn duyệt phiếu này?');" type="submit" name="duyet" value="duyet">Duyệt</button>
						
		</div>
		</form>
	</div>
</html>