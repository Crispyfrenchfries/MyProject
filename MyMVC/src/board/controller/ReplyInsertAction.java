package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAOMyBatis;
import board.model.ReplyVO;
import common.controller.AbstractAction;
import common.util.CommonUtil;

public class ReplyInsertAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//userid, idx_fk, content
		String userid=req.getParameter("userid");
		String idx_fk=req.getParameter("idx_fk");
		String content=req.getParameter("content");
		
		if(userid==null||idx_fk==null||userid.trim().isEmpty()||idx_fk.trim().isEmpty()) {
			this.setViewPage("../boardList.do");
			this.setRedirect(true);
			return;
		}
		
		//ReplyVO에 담기
		ReplyVO reply=new ReplyVO(null,userid,content,null,idx_fk);
		//BoardDAOMybatis의 insertReply 호출후 메시지 처리
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		int n=dao.insertReply(reply);
		this.setViewPage("../boardView.do?idx="+idx_fk);
	      this.setRedirect(true);
		

	}

}
