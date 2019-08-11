package board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardListAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//BoardDAOMyBatis dao = new BoardDAOMyBatis();
		//테스트
		//int count=dao.getCount();
		//req.setAttribute("count", count);
		//this.setViewPage("board/testMybatis.jsp");
		HttpSession ses=req.getSession();
		
		//보여줄 페이값 받기
		String cpStr=req.getParameter("cpage");
		if(cpStr==null||cpStr.trim().isEmpty()) {
			cpStr="1";
		}
		int cpage=Integer.parseInt(cpStr.trim());
		
		String psStr=req.getParameter("pageSize");
		if(psStr==null) {
			//세션에 저장된 pageSize가 있는지 체크
			psStr=(String)ses.getAttribute("pageSize");
			if(psStr==null) {
				psStr="10";//디폴트 페이지 사이즈를 10개 지정
			}
		}
		int pageSize=Integer.parseInt(psStr);
		ses.setAttribute("pageSize", psStr);
		
		//검색어 받기
		String findType=req.getParameter("findType");
		String findKeyword=req.getParameter("findKeyword");
		
		Map<String, String> map=new HashMap<>();
		map.put("findType", findType);
		map.put("findKeyword",findKeyword);

		//총 게시물 수 구학
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		
		int totalCount=dao.getTotalCount(map);//검색유형,검색어
		//페이지수 구하기
		int pageCount=(totalCount-1)/pageSize+1;
		if(cpage<0) {
			cpage=1;
		}
		if(cpage>pageCount) {
			cpage=pageCount;
		}
		int end=cpage*pageSize;
		int start=end-(pageSize-1);
		/*페이지 블럭 처리 관련
		 * [1][2][3][4][5] | [6][7][8][9][10] | [11][12]....
		 * cpage 		pagingBlock 	prevBlock 	 nextBlock
		 * 1~4, 5			5개 				0			 6
		 * 6~9, 10 			5				5			 11
		 * 11~14, 15		5				10			 16
		 * prevBlock=(cpage-1)/pagingBlock*pagingBlock
		 * nextBlock=prevBlock+(paginginBlock+1) 
		 * */
		int pagingBlock=10;//5개 단위로 페이지 묶음 처리
		int prevBlock=(cpage-1)/pagingBlock*pagingBlock;
		int nextBlock=prevBlock+(pagingBlock+1);
		
		
		map.put("start", String.valueOf(start));
		map.put("end", String.valueOf(end));
		
		
		List<BoardVO> arr = dao.listBoard(map);
		
		req.setAttribute("boardArr", arr);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("cpage", cpage);
		req.setAttribute("pagingBlock", pagingBlock);
		req.setAttribute("prevBlock", prevBlock);
		req.setAttribute("nextBlock", nextBlock);
		req.setAttribute("findType", findType);
		req.setAttribute("findKeyword", findKeyword);
		
		System.out.println("pageCount==="+pageCount);
		this.setViewPage("board/boardList2.jsp");
		this.setRedirect(false);
		

	}

}
