package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.util.CommonUtil;
import user.model.NotUserException;
import user.model.UserDAO;
import user.model.UserVO;

public class LoginAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//아이디, 비번, 아이디 저장 파라미터 값 받기
		String userid=req.getParameter("userid");
		String pwd=req.getParameter("pwd");
		String saveId=req.getParameter("saveId");//쿠키사용
		if(userid==null||pwd==null||userid.trim().isEmpty()||pwd.trim().isEmpty()) {
			String loc="index.do";
			String viewPage=CommonUtil.addMsgLoc(req,"잘못들어온 경로입니다", loc);
			this.setViewPage(viewPage);
			this.setRedirect(false);
			return;
		}
		UserDAO dao=new UserDAO();
		try{//아이디에 해당하는 User정보를 받아온다
		UserVO user=dao.selectByUserid(userid);
	
		if(!user.getPwd().equals(pwd)) {
			//비번이 일치하지 않는다면
			throw new NotUserException("비밀번호가 일치하지 않아요");
		}
		//로그인 인증을 받았다면 해당 회원정보를 세션에 저장한다.
		HttpSession ses=req.getSession();
		ses.setAttribute("loginUser", user);
		
		//세션에 돌아갈 페이지가 저장되어 있는지 꺼내보자.
		//있으면 해당 페이지로 돌아가고, 없으면 index.do로 돌아가자
		String returnPage=(String)ses.getAttribute("returnPage");
		
		if(returnPage==null) {
			this.setViewPage("index.do");			
		}else {
			this.setViewPage(returnPage);
		}
		this.setRedirect(true);
		
	}catch(NotUserException e) {
		String msg=e.getMessage();
		this.setViewPage(CommonUtil.addMsgBack(req, msg));
		this.setRedirect(false);
	 }
	}
}
