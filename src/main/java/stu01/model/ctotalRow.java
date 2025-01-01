package stu01.model;

public class ctotalRow{
	private int checkin;
	private cera era;
	public int getCheckin() {
		return checkin;
	}
	public void setCheckin(int checkin) {
		this.checkin = checkin;
	}
	public cera getEra() {
		return era;
	}
	public void setEra(cera era) {
		this.era = era;
	}
	public ctotalRow(int checkin,cera era){
		this.checkin=checkin;
		this.era=era;
		
	}
}
