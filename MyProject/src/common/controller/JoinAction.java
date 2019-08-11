package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.CommonUtil;

public class JoinAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String u_name=req.getParameter("u_name");
		String userid=req.getParameter("userid");
		String pwd=req.getParameter("pwd");
		String email=req.getParameter("email");
		
		if(u_name==null||u_name.trim().isEmpty()) {
			String view=CommonUtil.addMsgLoc(req, "�̸��� �Է����ּ���", "index.do");
			this.setViewPage(view);
			this.setRedirect(false);
		}//if
		if(userid==null||userid.trim().isEmpty()) {
			String view=CommonUtil.addMsgLoc(req, "���̵� �Է����ּ���", "index.do");
			this.setViewPage(view);
			this.setRedirect(false);
		}//if
		if(pwd==null||pwd.trim().isEmpty()) {
			String view=CommonUtil.addMsgLoc(req, "��й�ȣ�� �Է����ּ���", "index.do");
			this.setViewPage(view);
			this.setRedirect(false);
		}//if
		if(email==null||email.trim().isEmpty()) {
			String view=CommonUtil.addMsgLoc(req, "�̸����� �Է����ּ���", "index.do");
			this.setViewPage(view);
			this.setRedirect(false);
		}//if
		
	
	}
	
}