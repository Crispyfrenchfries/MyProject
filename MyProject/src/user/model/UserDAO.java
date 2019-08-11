package user.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.util.DAOBase;
import common.util.NotUserException;

public class UserDAO extends DAOBase{
	

	private ArrayList<UserVO> makeList(ResultSet rs) throws SQLException {
		ArrayList<UserVO> arr=new ArrayList<>();
		while(rs.next()) {
			int num=rs.getInt("USER_NUM");
			String name=rs.getString("USER_NAME");
			String userid=rs.getString("USER_ID");
			String pwd=rs.getString("USER_PWD");
			String email=rs.getString("USER_EMAIL");
			
			UserVO user=new UserVO(num, name, userid, pwd, email);
			arr.add(user);
		}
		return arr;
	}
	
	
	//회원정보 가져오는 메소드
	public UserVO selectByUserid(String userid) throws SQLException, NotUserException{
	try {
		con=ds.getConnection();
		String sql="SELECT *FROM TRAVEL_USER WHERE USER_ID=?";
		ps=con.prepareStatement(sql);
		ps.setString(1, userid);
		rs=ps.executeQuery();
		List<UserVO> arr=makeList(rs);
		if(arr!=null&&arr.size()==1) {
			UserVO user=arr.get(0);
			return user;
		}//if
		
		throw new NotUserException(userid+"란 아이디는 존재하지 않습니다");
		
	}finally {
		close();
	}
		
}
	public UserVO loginCheck(String userid, String pwd) throws SQLException, NotUserException {
		UserVO user=this.selectByUserid(userid);
		if(user!=null) {
			if(user.getPwd().equals(pwd)) {	
			return user;
			}else {
				throw new NotUserException("비밀번호가 틀려요");
			}
		}
		
		return null;
		
	} 
	/** 회원가입 메소드
	 * @throws SQLException */
	public int InsertUser(String name, String userid, String pwd, String email) throws SQLException {
		try {
		con=ds.getConnection();
		String sql="";
		ps=con.prepareStatement(sql);
		ps.setString(1, name);
		ps.setString(2, userid);
		ps.setString(3, pwd);
		ps.setString(4, email);
		int n=ps.executeUpdate();
		return n;
		}finally {
			close();
		}
		
	}
	
	
	
	
}
	
