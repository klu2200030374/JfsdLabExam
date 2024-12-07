package com.klef.jfsd.exam;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public class Course extends Employee
{
	@Id
	@Column(name="cname",length = 50)
	   private String name;
	  @Column(name="ccredits",length = 50)
	   private String credits;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCredits() {
		return credits;
	}
	public void setCredits(String credits) {
		this.credits = credits;
	}
	  
	
}
