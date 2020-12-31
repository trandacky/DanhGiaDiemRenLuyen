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
	<form action="/gvhd/duyetlan2/loc" method="post">
		<div class="content form-control">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-2">
						<div class="form-group">
							<label>Năm học </label> <select class="form-control"
								name="namhoc">
								<option value="${namhocselect}">${namhocselect}-${namhocselect+1}</option>
								<%
									for (int i = 2020; i < 2200; i += 1) {
								%>
								<option value="<%=i%>"><%=i%>-<%=i + 1%></option>
								<%
									}
								%>
							</select>
						</div>
					</div>
					<div class="col-md-2">

						<div class="form-group">
							<label>Học kỳ </label> <select class="form-control" name="hocky">
								<option value="${selecthocky}">${selecthocky}</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
							</select>

						</div>
					</div>
					<div class="col-md-12">
						<div>
							<button class="btn btn-primary" type="submit">Lọc Danh
								sách</button>
							<a href="/gvhd/duyetlan2"><input type="button"
								class="btn btn-primary" value="Làm mới"></a>

						</div>
					</div>
				</div>
			</div>
		</div>
	</form>

	<div>
		<form class="form-inline my-2 my-lg-0 form-control"
			action="/gvhd/duyetlan2/seach" method="GET">
			<input class="form-inline my-2 my-lg-0 form-control" type="search"
				placeholder="Search" aria-label="Search" name="seach">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit"
				onclick="#">Search</button>
		</form>

	</div>

	<div class="text-center">

		<table class="table table-striped">
			<tr>
				<th>Mã sinh viên</th>
				<th>Họ tên</th>
				<th>Ngày sinh</th>
				<th>Điểm lần 1</th>
				<th>Điểm lần 2</th>
				<th>Quyền</th>
				<th>Duyệt</th>
			</tr>
			<c:forEach items="${listPhieuRenLuyen}" var="phieu">
				<form action="/gvhd/duyetlan2/duyetmot" method="post">
					<tr>
						<td><input type="hidden" name="idphieu"
							value="${phieu.getIdPhieuRenLuyen()}"><a
							href="/gvhd/duyetlan2/${phieu.getIdPhieuRenLuyen()}">${phieu.getMaSinhVien().getMaSinhVien()}</a></td>
						<td><a href="/gvhd/duyetlan2/${phieu.getIdPhieuRenLuyen()}">${phieu.getMaSinhVien().getTen()}</a></td>
						<td>${phieu.getMaSinhVien().getNgayThangNamSinh()}</td>
						<td>${phieu.getTongDiemLan1()}</td>
						<td>${phieu.getTongDiemLan2()}</td>

						<td><button type="button"
								style="${phieu.getMaSinhVien().getQuyen()==0 ? 'background-color: red':'background-color: lightgreen'}"
								class="btn btn-primary" disabled>${phieu.getMaSinhVien().getQuyen()}</button></td>
						<td><button type="submit"
								style="${phieu.getDaDuyetLan2()==true ? 'background-color: lightgreen':'background-color: red'}"
								class="btn btn-primary">${phieu.getDaDuyetLan2()}</button></td>
					</tr>
				</form>
			</c:forEach>

		</table>

		<button class="btn btn-primary" style="float: right;" form="formloc"
			name="xuatfile" value="xuatfile" type="submit">Xuất file
			excel</button>
		<form action="/gvhd/duyetlan2/duyettatca" method="post">
			<button class="btn btn-primary pull-right"
				onclick="return confirm('Bạn muốn duyệt cho toàn bộ lớp?');"
				style="float: right;" type="submit">Duyệt tất cả</button>
		</form>
	</div>
</body>
</html>