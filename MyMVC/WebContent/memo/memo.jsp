<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/top.jsp"/>
    	<div class="row">
    	<div class="col-md-10 col-md-offset-1 text-center">
    		<h1 class="text-primary mt-5 mb-3 text-center">한줄 메모장</h1>
		<form name="f" action="memoAdd.do" method="post">
		<table class="table table-bordered">
			<tr>
				<td class="text-center"><h4>작성자</h4></td>
				<td>
					<input type="text" name="name"
					 placeholder="User Name" class="form-control">
				</td>
			</tr>
			<tr>
				<td class="text-center"><h4>메모내용</h4></td>
				<td>
					<input type="text" name="msg"
					 placeholder="Message" class="form-control"
					 maxlength="100"> <!-- maxlength => 최대 입력 가능 글자개수 100개로 지정하는  것 -->
				</td>
			</tr>
			<tr>
				<td colspan="2" class="text-right">
					<button type="button" onclick="check()" class="btn btn-primary">메모 남기기</button>
					<button type="reset" class="btn btn-warning">다시쓰기</button>
				</td>
			</tr>
		</table>
	</form>
 		</div>
 </div>
 <script type="text/javascript">
		var check = function(){
			if(!f.name.value){
				alert('작성자를 입력하세요.');
				f.name.focus();
				return;
			}
			if(!f.msg.value){
				alert('메모 내용을 입력하세요.');
				f.msg.focus();
				return;
			}
			f.submit(); //서버로 전송하는 기능
		}
	</script>
  <jsp:include page="/foot.jsp"/>
  