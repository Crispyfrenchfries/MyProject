package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.util.CommonUtil;
import common.util.NotUserException;
import user.model.UserDAO;
import user.model.UserVO;

public class LoginAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String userid=req.getParameter("userid");
		String pwd=req.getParameter("pwd");
		System.out.println(userid+"/"+pwd);
		String saveId=req.getParameter("saveId");
		if(userid==null||pwd==null||userid.trim().isEmpty()||pwd.trim().isEmpty()) {
			String loc="index.do";
			this.setRedirect(true);
			this.setViewPage(loc);
			return;
		}
	
		UserDAO dao=new UserDAO();
		try {
		UserVO user=dao.selectByUserid(userid);
		
		if(!user.getPwd().equals(pwd)) {
			
			throw new NotUserException("비밀번호가 일치하지 않아요");
		}
		HttpSession ses=req.getSession();
		ses.setAttribute("loginUser", user);
		
		String returnPage=(String)ses.getAttribute("returnPage");
		if(returnPage==null) {
			this.setViewPage("index.do");
		}else {
			this.setViewPage(returnPage);
		}
		this.setRedirect(true);
		
		}catch(NotUserException e){
			String msg=e.getMessage();
			this.setViewPage(CommonUtil.addMsgBack(req, msg));
			this.setRedirect(false);
		}
		
	}
}


