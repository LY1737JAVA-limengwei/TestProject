package com.keyan.pojo;

public class Notice {
	private int id;//通知信息编号
	private String nitile;//通知信息标题
	private String ncontent;//通知信息内容
	private String ntime;//通知发布时间
	private String ndepartid;//发布人姓名
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNitile() {
		return nitile;
	}
	public void setNitile(String nitile) {
		this.nitile = nitile;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public String getNtime() {
		return ntime;
	}
	public void setNtime(String ntime) {
		this.ntime = ntime;
	}
	public String getNdepartid() {
		return ndepartid;
	}
	public void setNdepartid(String ndepartid) {
		this.ndepartid = ndepartid;
	}
	@Override
	public String toString() {
		return "Notice [id=" + id + ", nitile=" + nitile + ", ncontent=" + ncontent + ", ntime=" + ntime
				+ ", ndepartid=" + ndepartid + "]";
	}
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Notice(int id, String nitile, String ncontent, String ntime, String ndepartid) {
		super();
		this.id = id;
		this.nitile = nitile;
		this.ncontent = ncontent;
		this.ntime = ntime;
		this.ndepartid = ndepartid;
	}
	
}
