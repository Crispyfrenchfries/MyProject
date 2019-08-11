package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.NotUserException;
import user.model.UserDAO;
import user.model.UserVO;

public class IdcheckAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String userid=req.getParameter("userid");

		UserDAO dao=new UserDAO();
		try {
			UserVO user=dao.selectByUserid(userid);
			if(user==null) {
				req.setAttribute("IdCheck", "사용가능" );
			}else {
				req.setAttribute("IdCheck", "사용불가능" );
			}
		}catch(NotUserException e) {
			
		}
		this.setViewPage("/login/IdchekResult.jsp");
	}

}
