package common.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/FileDown")


public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			download(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		download(request,response);
	}
	
	private void download(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException{
		//1.�ٿ�ε��� ���ϸ� �ޱ�
		String fname=req.getParameter("filename");
		System.out.println("fname="+fname);
		//2. ���ε� ���丮�� ������ ���
		//jsp������ application.getRealPath("/upload");
		ServletContext application=req.getServletContext();
		String upDir=application.getRealPath("/board/Upload");
		//3. ������ �����
		String path=upDir+File.separator+fname;
		System.out.println(path);
		/* 
		 * Ŭ���̾�Ʈ�ʿ� ���� �ٿ�ε� â�� �����
		 * response ����� ����Ʈ Ÿ���� �����ϴµ�,
		 * �������� ����ƮŸ���� �� �� �ִ� �����̸� �Ľ� �����ְ�
		 * �� �𸣴� �����̸� �ٿ�ε�â�� ����.
		 * 
		 * */
		//res.setContentType("application.unknown");
		res.setContentType("applcation/octet-stream");//�������α׷� �������� ����
		
		//�ѱ����ϸ��� ������ ���� ���� ����
		String fname_en=new String(fname.getBytes(),"ISO-8859-1");
		
		
		//�ٿ�ε� â�� �����Ŵ
		String arg1="attachment; filename="+fname_en;
		//res����� �ٿ�ε��� ���ϸ��� ����
		res.setHeader("Content-disposition", arg1);
		
		//4.��Ʈ�� �����ؼ� �а� ������ ���ؼ� ��������
		//������ �ҽ�: (����-> path)-> FileInputStream -> BufferedInputStream
		//������ ������: Ŭ���̾�Ʈ(������-response)->ServletOutputStream->
		//						BufferedOutputStream
		
		BufferedInputStream bis= new BufferedInputStream(new FileInputStream(path));
		
		BufferedOutputStream bos=new BufferedOutputStream(res.getOutputStream());
		
		byte[] data= new byte[4000];
		int n=0;
		while((n=bis.read(data))!=-1) {
			bos.write(data,0,n);
			bos.flush();
		}
		bos.close();
		bis.close();
		
	}

}
