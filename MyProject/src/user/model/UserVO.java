package user.model;

import java.io.Serializable;

public class UserVO implements Serializable{
	
	private int u_num;
	private String u_name;
	private String userid;
	private String pwd;
	private String email;
	
	
	
	public UserVO(int u_num, String u_name, String userid, String pwd, String email) {
		super();
		this.u_num=u_num;
		this.u_name=u_name;
		this.userid=userid;
		this.pwd=pwd;
		this.email=email;
	}



	public int getU_num() {
		return u_num;
	}



	public void setU_num(int u_num) {
		this.u_num = u_num;
	}



	public String getU_name() {
		return u_name;
	}



	public void setU_name(String u_name) {
		this.u_name = u_name;
	}



	public String getUserid() {
		return userid;
	}



	public void setUserid(String userid) {
		this.userid = userid;
	}



	public String getPwd() {
		return pwd;
	}



	public void setPwd(String pwd) {
		this.pwd = pwd;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
