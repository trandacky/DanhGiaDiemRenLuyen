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
$.confirm({
    title: 'Confirm!',
    content: 'Simple confirm!',
    confirm: function(){
        alert('Confirmed!');
    },
    cancel: function(){
        alert('Canceled!')
    }
});
</style>
<body>
<form method="post" action="" id="formloc">
<div class="content form-control">
		<div class="container-fluid">
			<div class="row">
			<div class="col-md-2">

					<div class="form-group">
						<label>Năm học </label> 
						<select class="form-control" name="namhoc">
								<%
									for (int i = 2020; i < 2200; i += 1) {
								%>
								<option value="<%=i%>"><%=i%>-<%=i+1 %></option>
								<%
									}
								%>
						</select>
					</div>
				</div>
				<div class="col-md-2">

					<div class="form-group">
						<label>Học kỳ </label> 
						<select class="form-control" name="hocky">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
						</select>
					</div>
				</div>
				<div class="col-md-2">
					<div> 
					<label>Công cụ</label> 
						<button class="btn btn-primary" type="submit">Lọc danh sách</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>
	<div>
		<form class="form-inline my-2 my-lg-0 form-control">
			<input class="form-control mr-sm-2" type="search"
				placeholder="Search" aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="button"
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
			</tr>
			<c:forEach items="${listPhieuRenluyen}" var="phieu">
				<tr>
					<td><a href="" onclick="abc()">${phieu.getMaSinhVien().getMaSinhVien()}</a></td>
					<td><a href="" onclick="abc()">${phieu.getMaSinhVien().getTen()}</a></td>
					<td>${phieu.getMaSinhVien().getNgayThangNamSinh()}</td>
					<td>${phieu.getTongDiemLan1()}</td>
					<td>${phieu.getTongDiemLan2()}</td>
					<td><button type="button"
							style="${phieu.getMaSinhVien().getQuyen()==0 ? 'background-color: red':'background-color: lightgreen'}"
							class="btn btn-primary" disabled>${phieu.getMaSinhVien().getQuyen()}</button></td>
				</tr>
			</c:forEach>			
		</table>
		<button class="btn btn-primary" style="float: right;" form="formloc" name="xuatfile" value="xuatfile" type="submit">Xuất file excel</button>	
	</div>
</body>
</html>