package board.model;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


 
public class BoardDAOMyBatis {

   private final String NS="board.model.BoardMapper";
   private SqlSession ses;
   //BoardMapper.xml에 기술되어 있는 namespace를 NS상수에 할당한다.(필수)
   
   public BoardDAOMyBatis() {
      
   }
   
   public SqlSessionFactory getSessionFactory() {
      String resource="common/config/mybatis-config.xml";
      InputStream is=null;
      try {
      is=Resources.getResourceAsStream(resource);
      SqlSessionFactoryBuilder sb=new SqlSessionFactoryBuilder();
      SqlSessionFactory factory=sb.build(is);
      return factory;
      
      }catch(Exception e){
         System.out.println(e);
         return null;
      }
   }//--------------------------------------------------
   
   public int getCount() {
	   try {
	   ses=this.getSessionFactory().openSession();
	   int cnt=ses.selectOne(NS+".tabCount");
	   return cnt;
	   }finally {
		   if(ses!=null) {
			   ses.close();
		   }
	   }
   }

public int insertBoard(BoardVO board) {
	
	try {
		ses=this.getSessionFactory().openSession(); //디폴트가 auto commit이 아님
		int n=ses.insert(NS+".insertBoard",board);
		if(n>0) {
			ses.commit();
		}else {
			ses.rollback();
		}
		return n;
	}finally {
		 if(ses!=null) {
			   ses.close();
		   }
	}
	
}//---------------------------------------
//총 게시글 가져오기
public int getTotalCount(Map<String, String> map) {
	try {
		ses=this.getSessionFactory().openSession(true);
		int total=ses.selectOne(NS+".getTotalCount",map);
		return total;
	}finally {
		close();
	}
}
public List<BoardVO> listBoard(Map<String, String> map){
	
	try {
		
		ses=this.getSessionFactory().openSession();
		//전달할 값이 많을 때는
		//파라미터로 map또는 VO객체를 전달
		List<BoardVO> arr = ses.selectList(NS+".listBoard", map);
		//db테이블의 컬럼명과 VO의 프로퍼티명이 일치해야 알아서 가져와서 VO에 set해줌.
		return arr;
		
	}finally {
		
		close();
	}
}

/*조회수 증가*/

private void close() {
	if(ses!=null) ses.close();
}


public boolean updateReadnum(String idx) {
	
	try {
		ses=this.getSessionFactory().openSession(true);
		//openSession(): commit이 수동임. commit() 또는 rollback
		//openSession(true); auto commit
		int n =ses.update(NS+".updateReadnum",idx);
		return (n>0)?true:false;
	}finally {
		close();
	}
}

public BoardVO getBoard(String idx) {
	
	try {
		ses=this.getSessionFactory().openSession(true);
		//PK로 가져오기 때문에 결과는 레코드 1개
		BoardVO board=ses.selectOne(NS+".getBoard",idx);
		return board;
	}finally {
		close();
	}
}

public int deleteBoard(String idx) {

	try {
		ses=this.getSessionFactory().openSession(true);
		int n=ses.delete(NS+".deleteBoard",idx);
		return n;
	} finally {
		close();
	}

}

public int editBoard(BoardVO board) {
	try {
		ses=this.getSessionFactory().openSession(true);
		int n=ses.update(NS+".updateBoard",board);
		return n;
	} finally {
		close();
	}///
	
}///

/**댓글 달기*/
public int insertReply(ReplyVO reply) {
	   try {
	      ses=this.getSessionFactory().openSession(true);
	      int n=ses.insert(NS+".insertReply",reply);
	      return n;
	   }finally {
	      close();
	   }
	}

/**댓글 목록 가져오기*/
public List<ReplyVO> listReply(String idx_fk){
	try {
		ses=this.getSessionFactory().openSession(true);
		return ses.selectList(NS+".listReply",idx_fk);
	}finally {
		close();
	}
}
public int replyCount(String idxStr) {
	   try {
	      ses=this.getSessionFactory().openSession(true);
	      return ses.selectOne(NS+".replyCount",idxStr);
	   }finally {
	      close();
	   }
	   
	}


}