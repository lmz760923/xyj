package stu01.model;

public class Category {
private Integer id;
private java.util.Date created_at;
private java.util.Date updated_at;
private String category;
private String file;
private String href;
private String description;

public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
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
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
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
}
