package common.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DAOBase {
	
	protected Connection con;
	
	protected DataSource ds;
	protected PreparedStatement ps;
	protected ResultSet rs;
	
	public DAOBase() {
	try {	
		Context ctx=new InitialContext();
		ds=(DataSource)ctx.lookup("java:comp/env/jdbc/mydev");
	}catch (NamingException e) {
		System.out.println("데이터 룩업 성공");
		e.printStackTrace();
	}
	
	}
	public void close() {
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
		}catch (Exception e) {
			System.out.println("error: "+e);
		}
	}
	
}
