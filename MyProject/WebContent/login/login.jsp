<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    Cookie cks[]=request.getCookies();
   	String uid="";
   	boolean isChk=false;
   	if(cks!=null){
   		for(Cookie c:cks){
   			String key=c.getName();
   			if(key.equals("saveID")){
   				uid=c.getValue();
   				isChk=true;
   				break;
   			}
   		}
   		
   	}//if
    
    %>


<!-- The Modal -->
<div class="modal" id="myModal" style="text-align: center">
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header" style="margin-left: 40%; font-weight: bold">
				<h4 class="modal-title">로그인</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<form method="POST" action="<%=request.getContextPath()%>/login.do"
					method="post">
					<div class="container">
						<div class="form-group">
							<label for="userid">아이디</label> <input type="text"
								class="form-control" id="userid" name="userid">
						</div>
						<div class="form-group">
							<label for="pwd">비밀번호 </label> <input type="password"
								class="form-control" id="pwd" name="pwd">
						</div>
						<div class="form-group form-check">
							<label class="form-check-label"> <input
								class="form-check-input" type="checkbox" name="checkbox">
								아이디 저장
							</label>
						</div>
						<button type="submit" class="btn btn-primary btn-block"
							style="font-weight: bold">로그인</button>
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

</div>
