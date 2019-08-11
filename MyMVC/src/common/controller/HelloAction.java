package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloAction extends AbstractAction{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("HelloAction excute()호출됨...");
		
		req.setAttribute("msg", "HelloAction에서 왔어요~~!!");
		//뷰 페이지 지정
		this.setViewPage("/hello.jsp");
		
		//이동방식 지정
		this.setRedirect(false); //forward방식으로 이동
		//this.setRedirect(true);
		
	}
	
}///////////////////////////////////////////////////////////////
