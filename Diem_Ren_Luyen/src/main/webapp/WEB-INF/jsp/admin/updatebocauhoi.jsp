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
	<form method="post" action="/quanly/bocauhoi/update">
		<div class="content form-control">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-1">
						<div class="form-group">
							<label>ID: </label> ${bocauhoi.get().getIdBoCauHoi()} <input
								name="idbocauhoi" value="${bocauhoi.get().getIdBoCauHoi()}"
								type="hidden"> <input name="tinhtrang"
								value="${bocauhoi.get().getTinhTrang()}" type="hidden">
						</div>
					</div>
					<div class="col-md-11">
						<div class="form-group">
							<label>Tên bộ câu hỏi: </label> <input class="form-control"
								name="tenbocauhoi" value="${bocauhoi.get().getTenBoCauHoi()}"
								placeholder="${bocauhoi.get().getTenBoCauHoi()}"> <input
								name="idbocauhoi" value="${bocauhoi.get().getTenBoCauHoi()}"
								type="hidden">
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
	<form method="post" action="/quanly/bocauhoi/capnhat/up">
		<div class="content form-control">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label>Nội dung câu hỏi: </label> 
							<select name = "layidcauhoi" class="form-control">
								<c:forEach items="${listCauHoi}" var="cauhoi">
									<option value="${cauhoi.idCauHoi}">${cauhoi.noiDungCauHoi}</option>
								</c:forEach>
							</select>

						</div>
					</div>
				</div>
				<input name="idbocauhoi" value="${bocauhoi.get().getIdBoCauHoi()}"
					type="hidden">
				<div class="row">
					<div class="col-md-12">
						<div class="clearfix" style="float: right;">
							<button class="btn btn-primary pull-right" onclick="demo()"
								type="submit">Thêm vào bộ câu hỏi</button>

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
				<th>Nội dung câu hỏi</th>
				<th>Điểm tối đa</th>

			</tr>
			<c:forEach items="${ListCauHoi}" var="cauhoi">
				<form method="post" action="/quanly/bocauhoi/doiquyen">
					<tr>
						<td>${cauhoi.idCauHoi}</td>
						<td>${cauhoi.noiDungCauHoi}</td>
						<td>${cauhoi.diemToiDa}</td>
						

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