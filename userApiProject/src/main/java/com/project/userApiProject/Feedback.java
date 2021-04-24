package com.project.userApiProject;

public class Feedback {

	public int  fid;
	public String name;
	public String email;
	public int contact;
	public String message;
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getContact() {
		return contact;
	}
	public void setContact(int contact) {
		this.contact = contact;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "feedback [fid=" + fid + ", name=" + name + ", email=" + email + ", contact=" + contact + ", message="
				+ message + "]";
	}

}
