package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;
import common.util.CommonUtil;

public class BoardEditAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//
		String idx=req.getParameter("idx");
		String pwd=req.getParameter("pwd");
		String mode=req.getParameter("mode");
		if(idx==null||pwd==null||mode==null) {
		String view=CommonUtil.addMsgBack(req, "잘못 들어온 경로입니다");
		this.setViewPage(view);
		this.setRedirect(false);
		return;
		}
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		BoardVO board=dao.getBoard(idx);
		
		if(!board.getPwd().equals(pwd)) {
			this.setViewPage(CommonUtil.addMsgBack(req, "비밀번호가 틀려요"));
			this.setRedirect(false);
			return;
		}
		//비번 일치시
		req.setAttribute("board", board);
		this.setViewPage("board/boardEdit.jsp");
		this.setRedirect(false);
	}

}
