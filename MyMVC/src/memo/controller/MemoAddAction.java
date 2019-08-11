package memo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import memo.model.MemoDAO;
import memo.model.MemoVo;
// /memoAdd.do==> MemoAddAction ==> /memo/message.jsp
public class MemoAddAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//req.setCharacterEncoding("UTF-8");
		String name=req.getParameter("name");
		String msg=req.getParameter("msg");
		if(name==null||msg==null||name.trim().isEmpty()) {
			//redirect로 메모 등록 폼 페이지로 이동
			this.setViewPage("memo.do");
			this.setRedirect(true);//redirect방식으로 이동
			return;
		}
		MemoVo memo=new MemoVo(0,name,msg,null);
		MemoDAO dao=new MemoDAO();
		int n=dao.insertMemo(memo);
		String str=(n>0)?"등록 성공":"등록 실패";
		String loc=(n>0)?"memoList.do":"javascript:history.back()";
		
		req.setAttribute("msg", str);
		req.setAttribute("loc", loc);

		this.setViewPage("/memo/message.jsp");//뷰페이지 지정
		this.setRedirect(false);
	}

}
