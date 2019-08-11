package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;

public class BoardPwdAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(!req.getMethod().equalsIgnoreCase("post")) {
			this.setRedirect(true);
			this.setViewPage("boardList.do");
			return;
		}
		
		String idx=req.getParameter("idx");
		String mode=req.getParameter("mode");
		
		int md=Integer.parseInt(mode);
		String title="";
		if(md==1) {
			title="글 편집";
		}else if(md==2) {
			title="글 삭제";
		}
		req.setAttribute("idx", idx);
		req.setAttribute("mode", mode);
		req.setAttribute("title", title);
		
		
		//비밀번호 입력받는 페이지로 이동
		this.setViewPage("board/boardPwd.jsp");
		this.setRedirect(false);
		
	}

}
