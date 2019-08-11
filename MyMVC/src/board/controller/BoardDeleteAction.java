package board.controller;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;
import common.util.CommonUtil;

public class BoardDeleteAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		 //	1. 글번호, 비밀번호 파라미터 값 받기
			String idx=req.getParameter("idx");
			String pwd=req.getParameter("pwd");
			String mode=req.getParameter("mode");//삭제 모드는 2
			if(idx==null ||pwd==null||mode==null) {
				this.setViewPage("boardList.do");
				this.setRedirect(true);
				return;
			}
			BoardDAOMyBatis dao=new BoardDAOMyBatis();
			BoardVO dbBoard=dao.getBoard(idx);
			System.out.println(pwd+"/ "+dbBoard);
			if(!pwd.equals(dbBoard.getPwd())) {
				req.setAttribute("msg", "비밀번호가 일치하지 않아요");
				req.setAttribute("loc", "javascript:history.back()");
				this.setViewPage("memo/message.jsp");
				this.setRedirect(false);
				return;
			}
			long fsize=dbBoard.getFilesize();
			if(fsize>0) { //첨부파일 존재
				String filename=dbBoard.getFilename();
				System.out.println(filename);
				ServletContext app=req.getServletContext();
				String upDir=app.getRealPath("/board/Upload");
				File delFile=new File(upDir+File.separator+filename);
				if(delFile.exists()) {
					delFile.delete();//파일 삭제 처리
				}
			}//if-----------
			//디비에서 해당 글 삭제
			int n =dao.deleteBoard(idx);
			String str=(n>0)?"삭제 성공":"삭제 실패";
			String loc=(n>0)?"boardList.do":"javascript:history.back()";
			String view=CommonUtil.addMsgLoc(req, str, loc);
			
			this.setViewPage(view);
			this.setRedirect(false);
			
			
			
			
			
			
			
			
		 // 	2. 해당 글의 비밀번호가 DB board테이블의 값고 일치할 경우 삭제 처리
		 //		[1]첨부파일이 있을 경우
		 //		서버에서 업로드된 파일을 삭제처리한 후 DB에서 해당 글을 삭제한다
		 // 	[2]첨부 파일이 없을경우 => 삭제
		 // 	
		 //

	}

}
