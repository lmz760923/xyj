package stu01.model;

public class cera {
	public int getTang() {
		return tang;
	}
	public void setTang(int tang) {
		this.tang = tang;
	}
	public int getSong() {
		return song;
	}
	public void setSong(int song) {
		this.song = song;
	}
	public int getXian() {
		return xian;
	}
	public void setXian(int xian) {
		this.xian = xian;
	}
	private int tang;
	private int song;
	private int xian;
	public cera(int ptang,int psong,int pxian){
		this.tang=ptang;
		this.song=psong;
		this.xian=pxian;
	}
}
