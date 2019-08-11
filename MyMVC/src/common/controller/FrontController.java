package common.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(
	      urlPatterns = { "*.do" }, 
	      initParams = { 
	            @WebInitParam(name = "config", value = "C:\\Myjava\\workspace\\MyMVC\\WebContent\\WEB-INF\\Command.properties")
	      })

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Map<String,Object> cmdMap = new HashMap<>();

	/*서블릿 실행시 첫 요청이 있을 때 딱 한번 호출되는 메소드
	 * init-param에 기술되어있는 config에 해당하는 value값 (Command.propertise)을 얻어온다.
	 * web.xml기술. 최근에는 어노테이션으로 대치됨
	 * */
	public void init(ServletConfig conf) throws ServletException {
		
		String props=conf.getInitParameter("config");
		System.out.println("init() props= "+props);
		Properties p = new Properties();
		//Command.properties파일에 있는 매핑정보를 Properties로 옮기자.
		try {
			FileInputStream fis = new FileInputStream(props);
			p.load(fis);
			if(fis!=null) fis.close();
			System.out.println(p.getProperty("/hello.do"));
			//"/hello.do라는 key 값에 해당하는 value값을 가져온다.
			
			//p에서 key값들만 추출하고,그에 해당하는 value값도 추출하여,해당 액션클래스들을 메모리에 올려
			//HashMap에 지정할 예정.
			Set<Object> set = p.keySet();
			
			for(Object key:set) {
				String cmd=key.toString(); //key값을 가짐 "/hello.do;
				String className=p.getProperty(cmd);
					//value값 "common.controller.HelloAction"
				System.out.println("cmd: "+cmd);
				System.out.println("className: "+className);
				if(className!=null) {
					className=className.trim(); //앞뒤 공백제거
					Class cmdClass=Class.forName(className);
					Object cmdInstance=cmdClass.newInstance();
					//해당 클래스를 객체화 시켜서 메모리에 올려놓는다.
					cmdMap.put(cmd,cmdInstance);
					//////////////////////////////////////////////
					
				}//if----------
			}//for------------------
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ServletException(ex);
		} 
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		requestProcess(request,response);
	}

	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestProcess(request,response);
	}

	private void requestProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException {
		//1.클라이언트 요청을 분석해서 SubController에게 요청을 처리하도록 위임한다.
		
		String uri=req.getRequestURI();
		System.out.println("uri: "+uri); // "/MyMVC/hello.do"
		
		String myctx=req.getContextPath(); // "/MyMVC
		
		int len=myctx.length(); //6
		
		String cmd = uri.substring(len); //"hello.do만 나옴 MyMVC를 버림
		Object instance=cmdMap.get(cmd); // HelloAction객체가 들어옴
		if(instance==null) {
			System.out.println("액션이 널");
			throw new ServletException(cmd+"액션이 널이에요");
		}//if
		
		AbstractAction action=(AbstractAction)instance;
		//서브 컨트롤의 execute()호출하기
		try {
			action.execute(req, res);
			String viewPage=action.getViewPage();
			if(viewPage==null) {
				System.out.println("뷰 페이지가 널입니다.");
				viewPage="test.html";
				//디폴트 페이지를 test.html로
			}
			//이동 방식에 따라 뷰 페이지로 이동
			if(action.isRedirect()) {
				//redirect방식(true)
				res.sendRedirect(viewPage);
			}else {
				//forward방식(false)
				RequestDispatcher dispatch=req.getRequestDispatcher(viewPage);
				dispatch.forward(req, res);
				//forward 이동
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			throw new ServletException(e);
		}
		
	}
}
