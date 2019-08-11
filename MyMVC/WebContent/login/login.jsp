<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <%
   	//쿠키 꺼내기 => 쿠키의 키값이 saveID가 있다면 value값 (사용자 아이디)을 꺼내서
   	//input name이 userid인 곳에 value값으로 넣어준다
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

<!-- modal dialog 시작-------------------------------->
<div class="modal" id="loginmodal">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- modal header -->
			<div class="modal-header">
				<h4 class="modal-title">login page</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<!-- modal body -->
			<div class="modal-body">
				<form action="<%=request.getContextPath() %>/login.do" method="POST">
					<div class="form-group">
						<label for="_userid">아이디</label> <input type="text" id="_userid"
							name="userid" placeholder="User ID" value="<%=uid%>"
							class="form-control" required>

					</div>
					<div class="form-group">
						<label for="pwd">비밀번호</label> <input type="password" id="_pwd"
							name="pwd" placeholder="Password" class="form-control" required>

					</div>
					<div class="form-group form-check">
						<label class="form-check-label"> <input
							class="form-check-input" <%=(isChk)?"checked":"" %>
							type="checkbox" id="_saveID" name="saveID">아이디 저장

						</label>
					</div>
					<button class="btn btn-primary">Login</button>
				</form>
			</div>
			<!-- modal footer -->
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<!-- modal dialog end---------------------------- -->

