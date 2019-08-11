<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<jsp:include page="/top.jsp"/>
    	<div class="row" style="color:yellow">
    	<div class="col-md-10 col-md-offset-1 text-center">
    		<h1>메모 목록 보기</h1>
    		<h3>총 게시글수:${totalCount}</h3>
    		
    		<table class="table table-striped">
    			<tr style="color:red">
    				<th>글번호</th>
    				<th>글내용</th>
    				<th>작성자</th>
    				<th>수정|삭제</th>
    			</tr>
    			<!-- --------------------------------------------- -->
    			<c:if test="${memoArr == null || empty memoArr }">
   						<%--memoArr.size() ==0 이랑 같은 뜻임 ==>>> empty memoArr --%>
   						<tr><td colspan="4"><b>데이터가 없습니다.</b></td></tr>
    			</c:if>
    			<c:if test="${memoArr!=null && not empty memoArr}">
    			<c:forEach var="memo" items="${memoArr}">
    			<tr style="color:red">
    				<td>${memo.idx}</td> <%--jsp로 하면 memo.getIdx()로 해야댐 --%>
    				<td>${memo.msg }</td>
    				<td>${memo.name }</td>
    				<td><a>수정</a>|<a href="memoDel.do?idx=${memo.idx}">삭제</a></td>
    			</tr>
    			</c:forEach>
    			</c:if>
    			
    			<!-- --------------------------------------------- -->
    		</table>
 		</div>
 		<!-- 페이지 네비게이션 --------------------------------------------->
 		<div class="row">
 			<div class="col-md-10 col-md-offset-1 text-center">
 					<ul class="pagination">
 					<!-- 
 					begin => 시작 값 
 					end => 끝 값
 					step => 증가치
 					items => 자료구조
 					-->
 					<c:forEach var="i" begin="1" end="${pageCount}" step="1">
 						<li><a href="memoList.do?cpage=${i}">${i}</a></li>
 					</c:forEach>
 					</ul>
 			</div>
 		</div>
 </div>
  <jsp:include page="/foot.jsp"/>