package stu01.model;

public class carousel {
	private int id;
	private String file;
	private String href;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public java.util.Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(java.util.Date created_at) {
		this.created_at = created_at;
	}
	public java.util.Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(java.util.Date updated_at) {
		this.updated_at = updated_at;
	}
	private java.util.Date created_at;
	private java.util.Date updated_at;

}
