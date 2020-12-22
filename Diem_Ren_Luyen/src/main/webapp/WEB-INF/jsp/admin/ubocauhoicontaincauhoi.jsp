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
<form method="post" action="/quanly/bocauhoi/them">
<div class="content form-control">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-10">
					<div class="form-group">
						<label>Tên bộ câu hỏi: </label> <input class="form-control" name="tenbocauhoi">
					</div>
				</div>
				<div class="col-md-2">

					<div class="form-group">
						<label>Tình trạng: </label> 
						<select class="form-control" name="tinhtrang">
							<option value="true">True</option>
							<option value="false">False</option>
						</select>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="clearfix" style="float: right;">
						<button class="btn btn-primary pull-right" type="submit">Thêm</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>
	<div>
	
		<form class="form-inline my-2 my-lg-0 form-control" action="/quanly/bocauhoi/seach" method="get">
			<input class="form-control mr-sm-2" type="search"
				placeholder="Search" aria-label="Search" name=seach>
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit"
				onclick="#">Search</button>

		</form>
	</div>
	<div class="text-center">
		<table class="table table-striped">
			<tr>
				<th>ID</th>
				<th>Tên Bộ Câu Hỏi</th>
				<th>Tình trạng</th>

			</tr>
			<c:forEach items="${ListBoCauHoi}" var="bocauhoi">
			<form method="post" action="/quanly/bocauhoi/doiquyen">
				<tr>
				
					<td ><input name="idbocauhoi" value="${bocauhoi.idBoCauHoi}" type="hidden"><a href="/quanly/bocauhoi/${bocauhoi.idBoCauHoi}">${bocauhoi.idBoCauHoi}</a></td>
					
					<td ><input name="tenbocauhoi" value="${bocauhoi.tenBoCauHoi}" type="hidden"><a href="/quanly/bocauhoi/${bocauhoi.idBoCauHoi}">${bocauhoi.tenBoCauHoi}</a></td>
					
					<td><button type="submit" 
							style="${bocauhoi.tinhTrang==true ? 'background-color: lightgreen':'background-color: red'}"
							class="btn btn-primary" name="tinhtrang" value="${bocauhoi.tinhTrang}">${bocauhoi.tinhTrang}</button></td>
							
				</tr>
				</form>
			</c:forEach>
		</table>	
	</div>
</body>
</html>