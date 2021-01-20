<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
	<form action="/cbl/danhsachsinhvien/tinhtongvaduyetphieu" method="POST">
		<table class="table table-striped">
			<tr>
				<th class="text-center">Nội dung đánh giá</th>
				<th class="text-center">Sinh viên tự đánh giá</th>
				<th class="text-center">Tập thể lớp đánh giá</th>
				<th class="text-center">Chú thích</th>
			</tr>
			<%
				{
				
				int tongdiemlan1=0;
				int tongdiemlan2=0;
				
				
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

					<td style="width: 700px"><%=sttcauhoi%>)
						${phieu.getIdCauHoi().getNoiDungCauHoi()}: (
						${phieu.getIdCauHoi().getDiemToiDa()}đ )</td>
					<td>${phieu.getDiemLan1()}</td>
					<td>
					<input width="55" class="form-control" name="diemlan2<%=sttcauhoi%>" value="${phieu.getDiemLan2()}" placeholder="${phieu.getDiemLan2()}">
						<input type="hidden" class="form-control" name="idchitietphieurenluyen<%=sttcauhoi%>" value="${phieu.getIdChiTietPhieuRenLuyen()}">
					</td>
					
					<td style="width: 200px">${phieu.getGhiChu()}</td>
				</tr>
				<%
					sttcauhoi++;
					sothutubocauhoi++;
				%>
			</c:forEach>
			<%
				}
			%>
			<tr class="text-center">
			<td style="float:right">Tổng điểm:</td>
			<td>${listChiTietPhieuRenLuyen.get(0).getIdPhieuRenLuyen().getTongDiemLan1()}</td>
			<td><input width="55" class="form-control" name="tongdiemlan2" value="${listChiTietPhieuRenLuyen.get(0).getIdPhieuRenLuyen().getTongDiemLan2()}" disabled></td>
			<td></td>
			</tr>
		</table>
		
		<input type="hidden" name="idphieu" value="${listChiTietPhieuRenLuyen.get(0).getIdPhieuRenLuyen().getIdPhieuRenLuyen()}">
		<div class="clearfix" style="float: right;">
						<a href="/cbl/danhsachsinhvien">
						<button class="btn btn-primary pull-right" type="button">Hủy và Thoát</button></a>
						<button class="btn btn-primary pull-right" onclick="return confirm('Bạn muốn duyệt phiếu này?');" type="submit">Duyệt</button>
		</div>
		</form>
	</div>
</html>