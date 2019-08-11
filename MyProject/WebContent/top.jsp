<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<title>국내여행 추천지</title>
<style>
    .dropdown:hover .dropdown-menu{
    display: block;
    margin-top: 0;
    }
    .dropdown-menu>.dropdown-item:hover{
        background-color: skyblue;
    }
    .carousel-inner img {
      width: 100%;
      height: 100%;
  }
  #pick{
    margin-right: 3%;
  }

</style>
</head>
<body>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <ul class="navbar-nav  mr-auto">
          
          <div class="container" style="margin-top: 6%">                   
                <div class="dropdown">
                  <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" style="font-weight: bold">
                    추천지역
                  </button>
                  <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">서울</a>
                    <a class="dropdown-item" href="#">경기</a>
                    <a class="dropdown-item" href="#">인천</a>
                    <a class="dropdown-item" href="#">강원</a>
                    <a class="dropdown-item" href="#">대전</a>
                    <a class="dropdown-item" href="#">대구</a>
                    <a class="dropdown-item" href="#">부산</a>
                    <a class="dropdown-item" href="#">광주</a>
                    <a class="dropdown-item" href="#">충청도</a>
                    <a class="dropdown-item" href="#">경상도</a>
                    <a class="dropdown-item" href="#">전라도</a>
                    <a class="dropdown-item" href="#">제주도</a>
                  </div>
                </div>
              </div>
              <div>
                <li class="nav-item active" >
                  <a class="nav-link" href="index.html"><h3>국내여행 추천사이트</h3></a>
                </li>
              </div>
        </ul>
        <ul class="navbar-nav  col-md-2">    
        <li class="nav-item">
                <div class="container">
                        <!-- Button to Open the Modal -->
                        <c:if test="${loginUser eq null}">
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" style="font-weight: bold">
                        로그인</button>
                        <%@ include file="/login/login.jsp" %>
                        </c:if>
                        <c:if test="${loginUser ne null}">
						<li>
						<a style="color:white">${loginUser.userid}님 로그인중</a>
						</li>
						<a href="${pageContext.request.contextPath}/logout.do">Logout</a>	                          
                         </c:if>
                      </div>
                      
                <li class="nav-item">
                    <div class="container">
                        <!-- Button to Open the Modal -->
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal2" style="font-weight: bold">
                          회원가입
                        </button>
                        <%@ include file="/login/join.jsp" %>
                      </div>
                </li>
                </ul>
              </nav>