<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
	<form method="post" action="/quanly/cauhoi/capnhat/up">
		<div class="content form-control">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-1">
						<div class="form-group">
							<label>ID: </label> ${cauhoi.idCauHoi} <input name="idcauhoi"
								value="${cauhoi.idCauHoi}" type="hidden">
						</div>
					</div>
					<div class="col-md-11">
						<div class="form-group">
							<label>Nội dung câu hỏi: </label> <input class="form-control"
								name="noidungcauhoi" value="${cauhoi.noiDungCauHoi}"
								placeholder="${cauhoi.noiDungCauHoi}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-2">
						<div class="form-group">
							<label>Bộ câu hỏi: </label> ${cauhoi.idBoCauHoi.getIdBoCauHoi()}
						</div>
					</div>
					<div class="col-md-10">
						<div class="form-group">
							<label>Điểm tối đa: </label>
							<input class="form-control"
								name="diemtoida" value="${cauhoi.diemToiDa}"
								placeholder="${cauhoi.diemToiDa}">
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
	
</body>
<script>
	function demo() {
		alert("Đã cập nhật!");
	}
</script>
</html>