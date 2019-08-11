package common.util;

public class NotUserException extends Exception {
	
	public NotUserException() {
		super("회원이 아닙니다.");//super()로 예외 메시지 등록
	}
	public NotUserException(String msg) {
		super(msg);
	}
}
