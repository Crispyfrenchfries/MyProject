package common.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DAOBase {
   protected DataSource ds;
   private ConnectionPoolBean pool;
   protected Connection con;
   
   
   protected PreparedStatement ps;
   protected ResultSet rs;
   
   public DAOBase() {
	   try {
		Context ctx = new InitialContext();
		ds=(DataSource)ctx.lookup("java:comp/env/jdbc/mydev");
		System.out.println("데이터소스 룩업 성공");
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   
   
   public ConnectionPoolBean getPool() {
	return pool;
}


public void setPool(ConnectionPoolBean pool) {
	this.pool = pool;
	System.out.println("커넥션풀 주입(setPool): "+pool);
}


public void close() {
      try {
         if(rs!=null) rs.close();
         if(ps!=null) ps.close();
         if(con!=null) con.close();
         //DBCP는 con.close()하면 연결을 끊는 것이 아니라 풀에 반납을 해준다.
         //if(con!=null) pool.returnConnection(con);
         
      }catch(Exception e) {
         System.out.println("error:"+e);
      }
   }
}