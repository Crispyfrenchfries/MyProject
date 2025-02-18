<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="/top.jsp" />
<%
   String ctx=request.getContextPath();
%>
<script type="text/javascript">
   var check=function(){
      if(!sf.findType.value){
         alert('검색 유형을 선택하세요');
         sf.findType.focus();
         return false;
      }
      
      if(!sf.findKeyword.value){
         alert('검색어를 입력하세요');
         sf.findKeyword.focus();
         return false;
      }
      sf.submit();
   }


</script>




<div class="section">
<div class="row">
   <div align="center" id="bbs" class="col-md-8 col-md-offset-2">
   <h1>Spring Board</h1>
   ${boardArr}
   <p>
      <a href="<%=ctx%>/boardForm.do">글쓰기</a>| <a
         href="<%=ctx%>/boardList.do">글목록</a>
      <p>
      <div class="section">
      <div class="row">
         <!-- 검색 폼 시작--------------------- -->
         <form name="sf" action="find"  onsubmit="return check()">
         <div class="col-md-2">
            <select name="findType" class="form-control">
               <option value="">::검색 유형::</option>
               <option value="1">글제목</option>
               <option value="2">작성자</option>
               <option value="3">글내용</option>
            </select>
         </div>
         <div class="col-md-7">
            <input type="text" name="findKeyword" class="form-control"
            placeholder="검색어를 입력하세요">
         </div>
         <div class="col-md-1">
            <button type="button" onclick="check()" class="btn btn-warning">검색</button>
         </div>   
         </form>
         <!-- 검색 폼 끝---------------------- -->
         
         <!-- 페이지 사이즈 폼 시작-------------- -->
         <form name="pf" id="pf" action="boardList">
            
            <div class="col-md-2">
            <select name="pageSize" class="form-control" onchange="submit()">
               <option value="">:::페이지 사이즈:::</option>
               
            </select>
            </div>
         </form>
      </div>
      </div>
      <table class="table table-condensed table-striped">
         <tr>
            <th>글번호</th>
            <th>제목</th>
            <th>글쓴이</th>
            <th>날짜</th>
            <th>조회수</th>
         </tr>
         <!-- ---------------------------- -->
         <c:if test="${boardArr==null || empty boardArr }">
            <tr>
               <td colspan="5"><b>게시글이 없습니다.</b></td>
            </tr>
         </c:if>
         <c:if test="${boardArr !=null and not empty boardArr }">
         <c:forEach var="board" items="${boardArr}">
            <tr>
               <td>
                  ${board.idx}
               </td>
               <td align="left">
               
               <a href="boardView.do?idx=${board.idx}">               
               <!-- ${board.subject} -->
               <c:out value="${board.subject}"></c:out>                  
               </a></td>
               <td>
               ${board.name}
               </td>
               <td>   
               ${board.wdate}
               </td>
               <td>
               ${board.readnum}
               </td>
            </tr>
            </c:forEach>
            </c:if>
         <!-- ----------------------------- -->
         <tr>
            <td colspan="3" class="text-center">
               <ul class="pagination">
               <c:forEach var="i" begin="1" end="${pageCount}">
                  <li
                     <c:if test="${cpage eq i}">
                        class='active'
                     </c:if>
                  ><a href="boardList.do?cpage=${i}">${i}</a></li>
               </c:forEach>
               </ul>
                               
            </td>
            <td colspan="2">총게시물수:
            <span class="text-danger" style="font-weight:bold">${totalCount}개</span> 
            <br>
             <span  class="text-danger" style="font-weight:bold" >${cpage}</span>
             /${pageCount} pages
            </td>
         </tr>
         
      </table>
      
   </div>
</div>   
<c:import url="/foot.jsp" />