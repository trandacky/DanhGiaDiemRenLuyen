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
				<button type="button"  class="btn btn-primary">Đăng xuất</button>
				</div>
			</div>
		</nav>
		<div class="form-control">
			<div class="row">
				<div class="col-2">
					<div class="nav flex-column nav-pills" id="v-pills-tab"
						aria-orientation="vertical">
						<a href="home.jsp"><img src="image/logoqnu.jpg"></a>
												
						<a class="nav-link active" id="v-pills-home-tab"
							href="quanlyphieurenluyen" aria-selected="true">Đánh giá rèn luyện</a>
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