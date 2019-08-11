package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.util.CommonUtil;
import user.model.UserVO;

/**
 *  로그인 여부를 체크하여 로그인 했을 때만 요청을 전송한다.
 */
@WebFilter("/user/*")
public class LoginCheckFilter implements Filter {

  	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
			throws IOException, ServletException {
		//System.out.println("loginchecfilter 들어옴");
		HttpServletRequest req2=(HttpServletRequest)req;
		HttpSession ses=req2.getSession();
		UserVO loginUser=(UserVO)ses.getAttribute("loginUser");
		if(loginUser==null) {
			//로그인 하지 않았다면  다음 필터로 처리되지 않도록 return
			String msg="로그인 해야 이동 가능합니다.";
			String loc="../index.do";
			String viewPage=CommonUtil.addMsgLoc(req2, msg, loc);
			RequestDispatcher dispatchar=req2.getRequestDispatcher(viewPage);
			dispatchar.forward(req2, res);
			return;//return 해야 다음 필터로 넘어가지 않음
		}
		
		chain.doFilter(req, res);
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
