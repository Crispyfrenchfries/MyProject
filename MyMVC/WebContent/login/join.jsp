<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- The Modal -->
<div class="modal" id="myModal2" style="text-align: center">
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header" style="margin-left: 37%; font-weight: bold">
				<h4 class="modal-title">회원가입</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<form method="POST" action="<%=request.getContextPath()%>/join.do">
					<div class="container">
						<div class="form-group">
							<label for="name">이름 </label> <input type="text"
								class="form-control" id="u_name" name="u_name">
						</div>
						<div class="form-group">
							<label for="id">아이디 </label> <input type="text"
								class="form-control" id="userid" name="userid">
						</div>
						<div class="form-group">
							<label for="pwd">비밀번호 </label> <input type="password"
								class="form-control" id="pwd" name="pwd">
						</div>
						<div class="form-group">
							<label for="pwdcheck">비밀번호 확인 </label> <input type="password"
								class="form-control" id="pwdcheck" name="pwdcheck">
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
