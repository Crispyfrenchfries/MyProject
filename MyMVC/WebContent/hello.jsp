<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%--core 태그 라이브러리. 주로 제어문들이 있음 --%>
    
    <%--View 페이지에서는 주로 JSTL 태그라이브러리를 이용해 간단한 제어문을 구현한다.
    	이때 출력문은 el표현식을 사용한다.
    	다운로드 사이트 : http://jakarta.apache.org ==> Taglib
    	
    	jar 파일 4개 => 컨택스트/WEB-INF/lib 아래 둔다.
    	
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello MyMVC</title>
</head>
<body>
<h1>Hello MyMVC</h1>
<c:if test="${msg!=null}">
<h2 style="color:red"><%=request.getAttribute("msg")%></h2>
<h2 style="color:green">${msg}</h2> <!-- el표현식 msg의 값을 출력해줌 -->
</c:if>
</body>
</html>