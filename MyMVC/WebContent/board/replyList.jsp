<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-striped">
	<tr>
		<th colspan="2"><h3 class="text-primary text-center">댓글 목록<span class="label label-danger">${replyCount}</span></h3></th>
	</tr>
	
	<!-- --------------------------- -->
	<c:forEach var="rvo" items="${replyList}">
	<tr>
		<td width="80%">
		${rvo.content}<span class="pull-right">[${rvo.wdate}]</span></td>
		<td class="text=center">${rvo.userid}</td>
	</tr>
	</c:forEach>
	<!-- --------------------------- -->

</table>