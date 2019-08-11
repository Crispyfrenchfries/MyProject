package common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/*
 * Filter�������̽��� ��ӹ޾� ���͸� �����.
 * �߻�޼ҵ� 3���� �������̵� �Ѵ�.
 * init() , doFilter() , destroy()
 * 
 * */


//��� ��û�� ������ EncodingFilter�� ���� ��û�� ó���ϰ� ��.
@WebFilter("/*")
public class EncodingFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//request�� ������ ���� ���� ó���� �ڵ尡 �ִٸ� �̰����� �����Ѵ�.
		//���� response�� ���� �Ŀ� ���� ó���� �ڵ尡 �ִٸ� ���⼭ ����
		//�츮�� ���⼭ �ѱ� ���ڵ��� ó������.
		request.setCharacterEncoding("UTF-8");
//		System.out.println("EncodingFilter�� �ѱ� ó����...");
		
		chain.doFilter(request, response);
		//�������� ���Ͱ� ���� �� �� ���� ���ͷ� ������� �ִ� �ڵ�
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
