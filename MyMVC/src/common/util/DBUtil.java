package common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	static String url="jdbc:oracle:thin:@localhost:1521:XE";
	static String user = "mydev";
	static String pwd = "tiger";
	
	static {
		//static initializer : main()�޼ҵ� ���ٵ� ���� �����ϴ� ��
		//���⼭ ����Ŭ ����̹��� �ε� ��Ű��.
		//System.out.println("Static block");
		
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("Driver Loding Sucess");
		}catch(ClassNotFoundException e) {
			System.out.println("Driver Loding Fail..: ");
			e.printStackTrace();
		}
	}
	
	//public static void main(String[] args) {
		//System.out.println("main()");
	//}
//----------------------------

public static Connection getCon() 
throws SQLException
	{
	Connection con = DriverManager.getConnection(url,user,pwd);
	return con;
	}
}
