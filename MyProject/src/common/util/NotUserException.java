package common.util;

public class NotUserException extends Exception {
	
	public NotUserException() {
		super("ȸ���� �ƴմϴ�.");//super()�� ���� �޽��� ���
	}
	public NotUserException(String msg) {
		super(msg);
	}
}
