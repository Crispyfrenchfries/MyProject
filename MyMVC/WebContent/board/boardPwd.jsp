<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/top.jsp"/>
<script type="text/javascript">
	$(function(){
	$('#btnBoard').click(function(){
		var pw=$('input[name="pwd"]').val();
		if(!pw){
			alert('비밀번호를 입력하세요');
			$('input[name="pwd"]').focus();
			return;
		}//if
		var md=$('#mode').val();
		if(md=='1'){
			//편집
			$('#pF').prop('action','boardEdit.do');
			
		}else if(md=='2'){
			//삭제
			$('#pF').prop('action','boardDel.do');
		}
		$('#pF').prop('method','POST');//비번이 있기 때문에 post로 가야함
		$('#pF').submit();
	})//click
	})//$()
</script>
<div class="row">
	<div class="col-md-10 col-md-offset-1">
		<h1>${title}</h1>
		<form name= "pF" id="pF" class="form-inline">
			<input type="hidden" name="mode" id="mode" value="${mode}">
			<input type="hidden" name="idx" id="idx" value="${idx}">
			<label>게시글 비밀 번호</label>
			<input type="password" name="pwd" class="form-control" placeholder="Password">
			<button type="button" id="btnBoard" class="btn btn-primary">${title}</button>
		</form>
		</div>
	</div>
<jsp:include page="/foot.jsp"/>