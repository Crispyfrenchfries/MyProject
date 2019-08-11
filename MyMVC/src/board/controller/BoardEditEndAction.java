package board.controller;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;
import common.util.CommonUtil;

public class BoardEditEndAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
				//1. 파일 업로드 처리
				//  (기존에 업로드한 파일이 있다면 삭제 후 업로드 처리)
				// MyMVC/board/Upload
				// String upDir=application.getRealPath("/board/Upload");
				
				ServletContext app = req.getServletContext();
				String upDir = app.getRealPath("/board/Upload");
				System.out.println(upDir);
				File dir=null;
				if(upDir==null) {
					dir=new File(app.getRealPath("/")+File.pathSeparator+"board/Upload");
				}else {
					dir=new File(upDir);
				}
				
				if(!dir.exists()) {
					//디렉토리 만들기
					dir.mkdirs();
				}
				//2.MultipartRequest생성하면 자동으로 업로드 된다.
				// Tomcat 8.5/lib/cos.jar 라이브러리에 있음
				MultipartRequest mr = new MultipartRequest(req, upDir,10*1024*1024,"UTF-8",new DefaultFileRenamePolicy());
				
				System.out.println("업로드  성공");
				
				//사용자가 입력한 파라미터 값 받아서 데이터베이스 테이블에 insert하기
				//request를 MultipartRequest가 가져가기 때문에 사용자가 입력한 값을 mr을 통해 받아와야 함
				String idx= mr.getParameter("idx");//수정할 글번호 받기
				String name=mr.getParameter("name");
				String subject=mr.getParameter("subject");
				String content=mr.getParameter("content");
				String pwd=mr.getParameter("pwd");
				//첨부파일명 얻기 => getParameter()로 얻어오면 안된다.(주의사항)
				//getFilesystemName("파라미터명")으로 얻어와야 함
				String filename=mr.getFilesystemName("filename"); // 첨부 파일명
				File file=mr.getFile("filename");
				long filesize=(file!=null)?file.length():0; // 첨부 파일크기
				
				//새로 첨부한 파일이 있다면, 기존에 업로드했던 파일을 삭제 처리하자
				if(filename!=null) {
					//hidden으로 가지고 있는 기존 파일명을 가져와 삭제 처리한다
					String oldfile=mr.getParameter("oldfile");
					System.out.println("oldfile=" +oldfile);
					if(oldfile!=null && !oldfile.trim().isEmpty())
					{
						//삭제 처리
						File delFile=new File(upDir+File.separator+oldfile);
						if(delFile.exists()) {
							boolean b=delFile.delete();
							System.out.println("파일 삭제 여부: "+b);
						}
					}
				}
				
				BoardVO board = new BoardVO(idx,name,pwd,subject,content,null,0,filename,filesize);
				BoardDAOMyBatis dao = new BoardDAOMyBatis();
				int n=dao.editBoard(board);
				String str=(n>0)?"글수정 성공":"글수정 실패";
				String loc=(n>0)?"boardList.do":"javascript:history.back()";
				
				String view=CommonUtil.addMsgLoc(req, str, loc);
				this.setViewPage(view);
				this.setRedirect(false);
				

			}

	}


