<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">

	function memCheck(){
		if(!frm.u_name.value){
			alert('이름을 입력하세요');
			frm.u_name.focus();
			return;
		}
		if(!frm.userid.value){
			alert('아이디를 입력하세요');
			frm.userid.focus();
			return;
		}
		if(!frm.pwd.value){
			alert('비밀번호를 입력하세요');
			frm.pwd.focus();
			return;
		}
		if(frm.pwdcheck.value==null||frm.pwdcheck.value!=frm.pwd.value){
			alert('비밀번호를 다시 확인해주세요');
			frm.pwd.focus();
			return;
		}
		if(!frm.email.value){
			alert('이메일을 입력해주세요');
			frm.email.focus();
			return;
		}
		frm.submit();
	}
	
	function idCheck(){
		//사용자가 입력한 userid값 받기
		var uid=$('#u_userid').val();
		if(!uid){
			alert('아이디를 입력하세요');
			$('#u_userid').focus();
			return;
		}//if-----
		$.ajax({
			type:'POST',
			url:'/idcheck.do?userid='+uid,
			dataType:'xml',
			cache:false,
			success:function(res){
				//alert(res);
				var b=Boolean(res.isUse);
				if(b){
				$('#idResult').html(uid+"는 사용가능합니다")
							  .css({color:'blue',fontWeight:'bold'});				
				}else{
					$('#idResult').html(uid+"는 이미 사용중인 아이디입니다.")
							  .css({color:'red',fontWeight:'bold'});					
				}

			},
			error:function(err){
				alert(err.status);
			}
		})
	}

	
</script>
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
				<form  name="frm" method="POST" action="<%=request.getContextPath()%>/join.do">
					<div class="container">
						<div class="form-group">
							<label for="name">이름 </label> <input type="text"
								class="form-control" id="u_name" name="u_name">
						</div>
						<div class="form-group">
							<label for="id">아이디 </label> 
							<input type="text"class="form-control" id="u_userid" name="userid"><p></p>
							<button type="button" class="btn btn-primary" onClick="idCheck()">중복확인</button>
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

						<button type="button" class="btn btn-primary btn-block"
							style="font-weight: bold" onClick="memCheck()">회원가입</button>
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
