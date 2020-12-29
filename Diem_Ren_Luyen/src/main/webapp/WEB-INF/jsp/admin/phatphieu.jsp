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
<form method="post" action="/quanly/phatphieu/send">
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

					<div class="form-group">
						<label>Lớp: </label> 
						<select class="form-control" name="idlop">
						<c:forEach items="${listLop}" var="lop">
							<option value="${lop.idLop}">${lop.tenLop}</option>
						</c:forEach>
						</select>
						
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="clearfix" style="float: right;">
						<button class="btn btn-primary pull-right" type="submit">Phát phiếu</button>
						<button class="btn btn-primary pull-right" onclick="return confirm('Bạn muốn phát phiếu cho toàn bộ các lớp?');" type="submit" value="true" name="phattoanbo">Phát toàn bộ các lớp</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>
<label>Phiếu mẫu:</label>
	<div class="content form-control">
		<div class="container-fluid">
			<div class="row">
			
				<div class="col-md-3">

					<div class="form-group">
						<label>Mã sinh viên: </label> <label>10254</label>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Lớp: </label> <label>CNTTK40A</label>
					</div>
				</div>
				<div class="col-md-3">

					<div class="form-group">
						<label>Họ tên: </label> <label>Trần Đắc Kỳ</label>
					</div>
				</div>
				<div class="col-md-3">

					<div class="form-group">
						<label>Ngày sinh: </label> <label>24/09/1999</label>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div >
		<table class="table table-striped">
			<tr>
				<th class="text-center">Nội dung đánh giá</th>
				<th class="text-center">Sinh viên tự đánh giá</th>
				<th class="text-center">Tập thể lớp đánh giá</th>
				<th class="text-center">Chú thích</th>
			</tr>
			<% { int sothutubocauhoi=1;%>
			<c:forEach items="${ListBoCauHoi}" var="bocauhoi">
			
				<tr>
					<td><%=sothutubocauhoi%>. ${bocauhoi.tenBoCauHoi}</td>
					<td></td><td></td><td></td>
					<%int sttcauhoi=1; %>
				</tr>
				
			<c:forEach items="${ListCauHoi=bocauhoi.getCauHois()}" var="cauhoi">
				<tr>
					
					<td class="text-center"  style="width:700px"><%=sttcauhoi%>) ${cauhoi.noiDungCauHoi}: ( ${cauhoi.diemToiDa}đ )</td>
					<td></td>
					<td></td>
					<td></td>
					<%sttcauhoi++; %>
				</tr>
			</c:forEach>
				<tr>
					<td style="float:right">Tổng điểm mục <%=sothutubocauhoi%>:  </td>
					<td class="text-center">12</td>
					<td class="text-center">12</td>
					<td></td>
				</tr>
				
			<% sothutubocauhoi++; %>
			</c:forEach>
			<% } %>
		</table>
	</div>
</html>