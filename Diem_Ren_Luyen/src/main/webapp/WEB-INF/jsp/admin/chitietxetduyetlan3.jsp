<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
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
				<th>ID chi tiết phiếu</th>
				<th>Điểm lần 1</th>
				<th>Điểm lần 2</th>
				<th>Điểm lần 3</th>
				<th>ID câu hỏi</th>
				<th>ID phiếu</th>
			</tr>
			<c:forEach items="${ListBoCauHoi}" var="bocauhoi">
				<tr>
					<td><a href="" onclick="abc()">${bocauhoi.idBoCauHoi}</a></td>
					<td>${bocauhoi.tenBoCauHoi}</td>
					<td></td>
					<td></td>
				</tr>
			</c:forEach>
		</table>
		<div>
		<div class="row">
				<div class="col-md-12">
					<div class="clearfix" style="float: right;">
						<button class="btn btn-primary pull-right" type="button"
							onclick="#">Hoàn thành</button>
						<button class="btn btn-primary pull-right" type="button"
							onclick="#">Hủy</button>
					</div>
				</div>
			</div>
		</div>
		</table>
		
	</div>
</body>
</html>