package com.keyan.pojo;

import java.sql.Date;
import java.util.Map;
import java.util.Objects;

public class KyProject {
	private int pid;
	private String apname;
	private String exname;
	private int pstate;//审批状态；1表示待审批、2表示已通过、3表示未通过
	private String ptitle;
	private String ptype;
	private String ptime;
	

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getApname() {
		return apname;
	}

	public void setApname(String apname) {
		this.apname = apname;
	}

	public String getExname() {
		return exname;
	}

	public void setExname(String exname) {
		this.exname = exname;
	}

	public int getPstate() {
		return pstate;
	}

	public void setPstate(int pstate) {
		this.pstate = pstate;
	}

	public String getPtitle() {
		return ptitle;
	}

	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}

	public String getPtype() {
		return ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public String getPtime() {
		return ptime;
	}

	public void setPtime(String ptime) {
		this.ptime = ptime;
	}

	@Override
	public String toString() {
		return "KyProject [pid=" + pid + ", apname=" + apname + ", exname=" + exname + ", pstate=" + pstate
				+ ", ptitle=" + ptitle + ", ptype=" + ptype + ", ptime=" + ptime + "]";
	}
	

	
	public KyProject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KyProject(int pid, String apname, String exname, int pstate, String ptitle, String ptype, String ptime) {
		super();
		this.pid = pid;
		this.apname = apname;
		this.exname = exname;
		this.pstate = pstate;
		this.ptitle = ptitle;
		this.ptype = ptype;
		this.ptime = ptime;
	}

	public String toJson(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
