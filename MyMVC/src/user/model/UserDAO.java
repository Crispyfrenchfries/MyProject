package user.model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.util.DAOBase;
import common.util.DBUtil;

public class UserDAO extends DAOBase{
	//DAOBase에  con ps rs 프로퍼티가 존재
	

		/**회원 가입 처리를 하는 메소드 inset*/
		public int createUser(UserVO user) throws SQLException{
			try{
				//con=DBUtil.getcon();
				//con=this.getPool().getConnection();
				con=ds.getConnection();
				String sql="insert into member values(member_seq.nextval,?,?,?,?,?,?,?,?,?,sysdate,1000,0)";
				ps=con.prepareStatement(sql);
				ps.setString(1, user.getName());
				ps.setString(2, user.getUserid());
				ps.setString(3, user.getPwd());
				ps.setString(4, user.getHp1());
				ps.setString(5, user.getHp2());
				ps.setString(6, user.getHp3());
				ps.setString(7, user.getPost());
				ps.setString(8, user.getAddr1());
				ps.setString(9, user.getAddr2());
				int n=ps.executeUpdate();
				return n;
			}finally {
				close();
			}
		}//-------------------------------------------
		/**아이디 중복체크 처리 메소드*/
		public boolean idCheck(String userid) throws SQLException{
			try {
				//con=DBUtil.getcon();
				//con=this.getPool().getConnection();
				con=ds.getConnection();
				//커넥션풀에서 커낵션을 빌려 사용
				String sql="select idx from member where userid=?";
				ps=con.prepareStatement(sql);
				ps.setString(1,userid);
				rs=ps.executeQuery();
				boolean isExists=rs.next();//커서 이동해서 회원번호가 있으면 true 없으면 false반환
				return !isExists;
				//사용가능하면 true, 사용불가능하면 false를 반환하자
			}finally {
				close();
			}
			
		}//-------------------------------------------------------
		public List<UserVO> findUser(String ftype, String keyword)
		throws SQLException{
			try {
				con=ds.getConnection();
				String colName="";
				int n=Integer.parseInt(ftype);
				if(n==1) {
					colName="name";
				}else if(n==2) {
					colName="userId";
				}else if(n==3) {
					colName="hp1||hp2||hp3";
				}
				StringBuilder sb=new StringBuilder("SELECT * FROM member")
						.append(" WHERE "+colName+" LIKE ?");
				String sql=sb.toString();
				System.out.println(sql);
				ps=con.prepareStatement(sql);
				ps.setString(1, "%"+keyword+"%");
				rs=ps.executeQuery();
				
				return makeList(rs);
			}finally {
				close();
			}
		}
		
		public List<UserVO> listUser() throws SQLException{
			try {
				//con=DBUtil.getcon();
				//con=this.getPool().getConnection();
				con=ds.getConnection();
				//String: 원본 불변의 법칙을 적용함. immutable 한 특징을 가짐
				//	 	따라서 +로 결합하면 그만큼 객체가 메모리에 올라감
				//		성능을 위해 StringBuffer나 StringBuilder를 이용하자
				//		메모리 버퍼에서 문자열을 삽입,삭제,수정 작업을 처리하는 클래스
				//String sql="SELECT * FROM member ORDER BY idx ";
				//ps = con.prepareStatement(sql);
				//rs = ps.executeQuery();
				StringBuilder sb=new StringBuilder("select * from member")
						.append(" order by idx asc");
				String sql=sb.toString();
				ps=con.prepareStatement(sql);
				rs=ps.executeQuery();
				
				return makeList(rs);
			}finally {
				close();
			}
		}
		
		/**회원번호(idx)-pk로 회원정보 가져오는 메소드*/
		public UserVO selectUser(int idx) throws SQLException{
			try {
				//con=DBUtil.getcon();
				con=ds.getConnection();
				String sql=("select * from member where idx=? ");
				ps=con.prepareStatement(sql);
				ps.setInt(1,idx);
				rs=ps.executeQuery();
				List<UserVO> arr=makeList(rs);
				if(arr!=null&& arr.size()==1) {
					UserVO user=arr.get(0);
					return user;
				}	
				return null;
			}finally {
				close();
			}
		}
		
		
		
		
		private ArrayList<UserVO> makeList(ResultSet rs) throws SQLException {
			ArrayList<UserVO> arr = new ArrayList<>();
			while (rs.next()) {
				int idx = rs.getInt("idx");
				String name = rs.getString("name");
				String userid=rs.getString("userid");
				String pwd=rs.getString("pwd");
				String hp1 = rs.getString("hp1");
				String hp2 = rs.getString("hp2");
				String hp3 = rs.getString("hp3");
				String post=rs.getString("post");
				String addr1=rs.getString("addr1");
				String addr2=rs.getString("addr2");
				java.sql.Date indate = rs.getDate("indate");
				int mileage=rs.getInt("mileage");
				int mstate=rs.getInt("mstate");
				// record=>MemoVo
				UserVO user = new UserVO(idx, name, userid ,pwd,hp1,hp2,hp3,post,addr1,addr2,indate,mileage,mstate);
				////////////
				arr.add(user);
				////////////
			} // while-----------
			return arr;
		}
		
		/**회원정보를 수정하는 메소드-UPDATE문 수행*/
		public int editUser(UserVO user) throws SQLException{
			try {
				//con=DBUtil.getcon();
				con=ds.getConnection();
				String sql="update member set name=?, userid=?, pwd=?, hp1=?, hp2=?, hp3=?, post=?, addr1=?, addr2=?, mstate=? where idx=?";
				ps=con.prepareStatement(sql);
				ps.setString(1, user.getName());
				ps.setString(2, user.getUserid());
				ps.setString(3, user.getPwd());
				ps.setString(4, user.getHp1());
				ps.setString(5, user.getHp2());
				ps.setString(6, user.getHp3());
				ps.setString(7, user.getPost());
				ps.setString(8, user.getAddr1());
				ps.setString(9, user.getAddr2());
				ps.setInt(10, user.getMstate());
				ps.setInt(11, user.getIdx());
				int n=ps.executeUpdate();
				
				return n;
			}finally{
				close();
			}
		}
		
		/**회원정보를 회원번호(idx)로 삭제하는 메소드*/
		public int deleteUser(int idx) throws SQLException{
			try {
				//con=DBUtil.getcon();
				con=ds.getConnection();
				String sql="delete from member where idx=?";
				ps=con.prepareStatement(sql);
				ps.setInt(1, idx);
				int n=ps.executeUpdate();
				return n;
				
			}finally {
				close();
			}
		}//
		
		
		//userid(UK)로 회원정보 가져오는 메소드
		public UserVO selectByUserid(String userid) throws SQLException, NotUserException{
			try {
				//con=DBUtil.getcon();
				con=ds.getConnection();
				String sql="select * from member where userid=?";
				ps=con.prepareStatement(sql);
				ps.setString(1, userid);
				rs=ps.executeQuery();
				List<UserVO> arr=makeList(rs);
				if(arr!=null&&arr.size()==1) {
					//해당 아이디를 가진 회원이 있다면 1명(Unique하므로)
					UserVO user=arr.get(0);
					return user;//해당 회원정보를 반환
				}//if
				
				//해당 아이디가 없다면 => 사용자 정의 예외를 발생시킨다.
				throw new NotUserException(userid+"란 아이디는 존재하지 않아요");
				
			}finally {
				close();
			}
		}
		
		public UserVO loginCheck(String userid, String pwd) 
				throws SQLException, NotUserException{
			UserVO user=this.selectByUserid(userid);
			if(user!=null) {
				//회원 아이디가 맞다면
				//비밀번호 일치여부를 체크하자
				if(user.getPwd().equals(pwd)) {
					//일치할경우
					return user;
				}else {
					//불일치할 경우
					throw new NotUserException("비밀번호가 틀려요");
				}
			}//if
			return null;//아이디가 없는 아이디인 경우
		}
		
		
	}/////////////////////////////////////////////////


