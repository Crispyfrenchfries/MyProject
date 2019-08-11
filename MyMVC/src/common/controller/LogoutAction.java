package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 세션변수 모두 무효화
		HttpSession ses=req.getSession();
		ses.invalidate();
		this.setViewPage("index.do");
		this.setRedirect(true);
		

	}

}
