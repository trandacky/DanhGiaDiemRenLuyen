<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Điểm rèn luyện</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<style>
.topright {
	position: absolute;
	top: 10px;
	right: 16px;
	font-size: 18px;
}
</style>
<body>
	<div>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarTogglerDemo01"
				aria-controls="navbarTogglerDemo01" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarTogglerDemo01">
				<ul class="nav nav-tabs">
					<li class="nav-item"><H3>Đánh giá điểm rèn luyện sinh viên</H3>
					</li>
					
				</ul>
				
				<div class="topright">
				<label >tên tài khoản của admin hay của ai đó</label>
				<a href="/logout"><button type="button"  class="btn btn-primary">Đăng xuất</button></a>
				</div>
			</div>
		</nav>
		<div class="form-control">
			<div class="row">
				<div class="col-2">
					<div class="nav flex-column nav-pills" id="v-pills-tab"
						aria-orientation="vertical">
						<a href="index.jsp"><img src="/image/logoqnu.jpg"></a>
						<a class="nav-link ${activecauhoi}" id="v-pills-home-tab"
							href="/quanly/cauhoi" aria-selected="true">Câu hỏi</a>
							
						<a class="nav-link ${activebocauhoi}" id="v-pills-profile-tab" href="/quanly/bocauhoi"
							aria-selected="false">Bộ câu hỏi</a>
						
						<a class="nav-link ${activelop}" id="v-pills-profile-tab" href="/quanly/lop"
							aria-selected="false">Lớp</a>
							
						<a class="nav-link ${activequanlydanhgialan3}"
							id="v-pills-messages-tab" href="/quanly/quanlydanhgialan3"
							aria-selected="false">Xét duyệt lần 3</a>
							
						<a class="nav-link ${activequanlytaikhoan}"
							id="v-pills-settings-tab" href="/quanly/taikhoan"
							aria-selected="false">Quản lý tài khoản</a>
							
						<a class="nav-link ${activemodangky}"
							id="v-pills-settings-tab" href="modangky"
							aria-selected="false">Mở đăng ký</a>
					</div>
				</div>
				<div class="col-10">
					<div class="tab-content" id="v-pills-tabContent">

						<c:import url="${page}" />

					</div>
				</div>

			</div>
		</div>
	</div>

</body>
</html>
