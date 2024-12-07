package com.klef.jfsd.exam;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public class Instructor extends Employee
{
	@Id
	   @Column(name="iname",length = 50)
	   private String name;
	   @Column(name="iemail",length = 50)
	   private String email;
	   @Column(name="iphoneno",length = 50)
	   private String phoneno;
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
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
}
