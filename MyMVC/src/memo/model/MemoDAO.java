package memo.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.util.DBUtil;

/*
 * SW아키텍처 중 Persistence(영속성) 계층
 * -데이터 처리 계층을 담당. 데이터베이스에 접속하여 CRUD기능을 수행한다.
 * -Data Access Object를 줄여 DAO라고 함.
 */
public class MemoDAO {
	
	private Connection con; 
	private PreparedStatement ps;
	private ResultSet rs;
	 

	public MemoDAO() {
		
	}
	
	/*메모글을 등록하는 메소드를 만들자.*/
	public int insertMemo(MemoVo memo) 
	throws SQLException
	{
		try {
			con=DBUtil.getCon();
			String sql = "INSERT INTO MEMO VALUES(MEMO_SEQ.NEXTVAL,?,?,SYSDATE)";
			ps=con.prepareStatement(sql);
			ps.setString(1, memo.getName());
			ps.setString(2, memo.getMsg());
			int n = ps.executeUpdate();
			return n;
			
		}finally {
			close();
		}
	}
	
	/*메모글을 삭제하는 메소드 -pk로 삭제 delete문*/
	public int deleteMemo(int idx) throws SQLException{
		try {
			con=DBUtil.getCon();
			//DELETE문 작성하기
			String sql = "DELETE FROM MEMO WHERE IDX=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1,idx);
			int n = ps.executeUpdate();
			return n;
		}finally {
			close(); // 자원반납
		}
		
	}//-------------------------------------------
	
	public int getTotalCount() throws SQLException{
		try {
			con=DBUtil.getCon();
			String sql="select count(idx) from memo";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			int total=0;
			if(rs.next()) {
				total=rs.getInt(1);
			}
			return total;
		}finally {
			close();
		}
	}
	/*모든 메모글을 가져오는 메소드 -select문 수행*/
	public ArrayList<MemoVo> listMemo(int start,int end) throws SQLException{
		try {
			
			con=DBUtil.getCon();
			//String sql ="SELECT * FROM MEMO ORDER BY IDX DESC";
			String sql="select * from(" + 
					" select rownum rn, a.* from" + 
					" (select * from memo order by idx desc)  a" + 
					" )where rn between ? and ?";
			
			ps=con.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			rs=ps.executeQuery();
			
			
			return makeList(rs);
			
		} finally {
			close();
		}
	}
	
	public MemoVo selectMemoByIdx(int idx)
	throws SQLException
	{
		try {
			con=DBUtil.getCon();
			String sql = "SELECT * FROM memo WHERE idx=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, idx);
			rs=ps.executeQuery();
			ArrayList<MemoVo> arr=makeList(rs);
			if(arr!=null && arr.size()==1) {
				MemoVo memo =arr.get(0);
				return memo;
			}
			
			
			return null;
		} finally {
			close();
		}
	}
	
	/*메모글을 수정처리하는 메소드*/
	public int updateMemo(MemoVo memo) throws SQLException{
		try {
			con = DBUtil.getCon();
			String sql = "UPDATE memo SET name=? , msg=? WHERE idx=?" ;
			ps=con.prepareStatement(sql);
			ps.setString(1, memo.getName());
			ps.setString(2, memo.getMsg());
			ps.setInt(3, memo.getIdx());
			int n = ps.executeUpdate();
			return n;
			
		}finally {
			close();
		}
	}
	
	
	public ArrayList<MemoVo> makeList(ResultSet rs)
	throws SQLException{
		ArrayList<MemoVo> arr = new ArrayList<>();
		while(rs.next()) {
			int idx=rs.getInt("idx");
			String name = rs.getString("name");
			String msg = rs.getString("Msg");
			java.sql.Date wdate =rs.getDate("wdate");
			//record=>MemoVo
			MemoVo memo = new MemoVo(idx,name,msg,wdate);
			arr.add(memo);
		}//while---
		
		return arr;
	}
	
	/*검색유형에 따라 검색하는 메소드 -SELECT문 . WHERE절에 LIKE절*/
	public ArrayList<MemoVo> findMemo(String colType, String keyword)
	throws SQLException
	{
		try {
			con=DBUtil.getCon();
			//SELECT * FROM MEMO WHERE MSG LIKE '%안%'
			String sql="" , colVal="";
			if(colType.equals("idx")) {
				sql="SELECT * FROM MEMO WHERE "+colType+"=?";
				colVal=keyword;
			}else {
				sql ="SELECT * FROM MEMO WHERE "+colType+" Like ?";
				colVal="%"+keyword+"%";
			}
			System.out.println(sql);
			ps=con.prepareStatement(sql);
			ps.setString(1,colVal);
			
			
			rs=ps.executeQuery();
			ArrayList<MemoVo> arr = makeList(rs);
			
			return arr;
			
		}finally {
			close();
		}
	}

	
	//DB 연결자원을 반납해 주는 메소드
	public void close() {
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(con!=null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	

}////////////////////////////////////////////////////////////////////////
