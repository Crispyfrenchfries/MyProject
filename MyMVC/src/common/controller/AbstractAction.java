package common.controller;


//command 인터페이스를 상속받아 execute()추상 메소드를 멤버로 갖는다.
//subController가 이 클래스를 상속 받을 예정
abstract public class AbstractAction implements Command{
	
	private String viewPage; //뷰 페이지 값을 가질 예정
	private boolean isRedirect;//이동 방식이 redirect면 true , forward면 false
	
	//setter,getter
	
	public String getViewPage() {
		return viewPage;
	}
	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	
}
