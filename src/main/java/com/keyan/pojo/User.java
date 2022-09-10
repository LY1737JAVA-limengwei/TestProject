package com.keyan.pojo;

import java.util.Map;
import java.util.Objects;

public class User {
	private int uid;
	private String uname;
	private String pwd;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", pwd=" + pwd + "]";
	}
	
	
	
	public User() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public User(int uid, String uname, String pwd) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.pwd = pwd;
	}
	
	public String toJson(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
