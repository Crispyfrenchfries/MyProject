package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;

public class BoardFormAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//뷰 페이지 지정
		//boarForm.do ==> board/boardWrite.jsp 보여주도록
		this.setViewPage("board/boardWrite.jsp"); //뷰 페이지 지정
		this.setRedirect(false);

	}

}
