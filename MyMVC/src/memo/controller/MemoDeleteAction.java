package memo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import memo.model.MemoDAO;

public class MemoDeleteAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String idxStr=req.getParameter("idx") ;
		if(idxStr==null||idxStr.trim().isEmpty()) {
			this.setViewPage("memoList.do");
			this.setRedirect(true);
			return;
		}//if-------
		int idx=Integer.parseInt(idxStr.trim());
		MemoDAO dao=new MemoDAO();
		int n=dao.deleteMemo(idx);
		
		String str=(n>0)?"삭제 성공":"삭제 실패";
		String loc="memoList.do";
		req.setAttribute("msg", str);
		req.setAttribute("loc", loc);
		this.setViewPage("memo/message.jsp");
		this.setRedirect(false);
	}

}



