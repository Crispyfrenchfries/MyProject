<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>국내 여행 사이트</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<style>
#toptitle {
	font-size: 45px;
	text-align: center;
	padding-top: 0%;
	padding-bottom: 0%;
	width: 100%;
	font-weight: bold;
}

.nav-link {
	color: white;
	font-weight: bold;
	font-size: 20px;
}

#capital:hover, #south:hover, #west:hover, #middle:hover {
	background-color: chocolate;
}

#imgtext {
	font-size: 30px;
	font-weight: bold;
	font-style: italic;
}

#loginArea {
	font-size: 17px;
}

#capital, #south, #west, #middle {
	position: relative;
}

#capital>ul, #south>ul, #west>ul, #middle>ul {
	padding: 0;
	z-index: 2;
	width: 100%;
	position: absolute;
	display: none;
}

#capital>ul>li, #south>ul>li, #west>ul>li, #middle>ul>li {
	display: block;
	width: 100%;
	background: #fff;
	color: #555;
	text-align: center;
	padding: 10px 0;
}

#capital:hover>ul, #south:hover>ul, #west:hover>ul, #middle:hover>ul {
	display: block;
	list-style-type: none;
}

#capital>ul>li:hover, #south>ul>li:hover, #west>ul>li:hover, #middle>ul>li:hover
	{
	background: #ccc;
	color: #fff;
}

.list {
	color: white;
	font-size: 15px;
	font-weight: bold;
}

#pick {
	margin-right: 3%;
}

.mtext {
	font-size: 35px;
	text-align: center;
	margin-top: 3%;
}
</style>

</head>

<body>
	<div>
		<!-- Korea Trips 영역 -->
		<div>
			<div id="toptitle">
				<span style="color: goldenrod"> <i class="fa fa-car"> </i>
				</span> <i>Korea Trips <span style="color: goldenrod"> <i
						class="fa fa-car"> </i>
				</span> <br> <span
					style="font-size: 20px; font-style: normal; text-align: center; margin-left: 14%">
						국내여행 추천 사이트</span>
				</i> <span id="loginArea" style="float: right; margin-right: 6%">
					<c:if test="${loginUser eq null}">
						<button type="button" class="btn btn-outline-primary"
							data-toggle="modal" data-target="#myModal"
							style="font-weight: bold">로그인</button>
						<%@ include file="/login/login.jsp"%>
					</c:if> <c:if test="${loginUser ne null}">
						<li><a style="color: white">${loginUser.userid}님 로그인중</a></li>
						<a href="${pageContext.request.contextPath}/logout.do">Logout</a>
					</c:if>
			</div>


			<!-- Button to Open the Modal -->
			<button type="button" class="btn btn-outline-primary"
				data-toggle="modal" data-target="#myModal2"
				style="font-weight: bold">회원가입</button>

			<!-- The Modal -->
			<div class="modal" id="myModal2" style="text-align: center">
				<div class="modal-dialog">
					<div class="modal-content">

						<!-- Modal Header -->
						<div class="modal-header"
							style="margin-left: 37%; font-weight: bold">
							<h4 class="modal-title">회원가입</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>

						<!-- Modal body -->
						<div class="modal-body">
							<form method="POST" action="">
								<div class="container">
									<div class="form-group">
										<label for="name">이름 </label> <input type="text"
											class="form-control" id="name" name="name">
									</div>
									<div class="form-group">
										<label for="id">아이디 </label> <input type="text"
											class="form-control" id="id" name="id">
									</div>
									<div class="form-group">
										<label for="pwd">비밀번호 </label> <input type="password"
											class="form-control" id="pwd" name="password">
									</div>
									<div class="form-group">
										<label for="pwdcheck">비밀번호 확인 </label> <input type="password"
											class="form-control" id="pwdcheck" name="passwordcheck">
									</div>
									<div class="form-group">
										<label for="email">이메일 </label> <input type="email"
											class="form-control" id="email" name="email">
									</div>

									<button type="submit" class="btn btn-primary btn-block"
										style="font-weight: bold">회원가입</button>
								</div>
							</form>
						</div>

						<!-- Modal footer -->
						<div class="modal-footer">
							<button type="button" class="btn btn-danger" data-dismiss="modal"
								style="font-weight: bold">닫기</button>
						</div>

					</div>
				</div>
			</div>
			</span>
		</div>
	</div>