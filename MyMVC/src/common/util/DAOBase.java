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
		System.out.println("�����ͼҽ� ��� ����");
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
	System.out.println("Ŀ�ؼ�Ǯ ����(setPool): "+pool);
}


public void close() {
      try {
         if(rs!=null) rs.close();
         if(ps!=null) ps.close();
         if(con!=null) con.close();
         //DBCP�� con.close()�ϸ� ������ ���� ���� �ƴ϶� Ǯ�� �ݳ��� ���ش�.
         //if(con!=null) pool.returnConnection(con);
         
      }catch(Exception e) {
         System.out.println("error:"+e);
      }
   }
}