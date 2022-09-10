package com.keyan.pojo;

public class Admin {
	private int aid;
	private String aname;
	private String pwd;
	public int getaid() {
		return aid;
	}
	public void setaid(int aid) {
		this.aid = aid;
	}
	public String getaname() {
		return aname;
	}
	public void setaname(String aname) {
		this.aname = aname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "Admin [aid=" + aid + ", aname=" + aname + ", pwd=" + pwd + "]";
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(int aid, String aname, String pwd) {
		super();
		this.aid = aid;
		this.aname = aname;
		this.pwd = pwd;
	}
	
}
