package com.klef.jfsd.exam;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity

@Inheritance(strategy = InheritanceType.JOINED)
public class Employee 
{
	   @Id
	   @GeneratedValue(strategy = GenerationType.AUTO)
	   private int id;
	   @Column(name="ename",length=50)
	   private String name;
	   @Column(name="esalary",length=50,unique = true)
	   private String salary;
	   @Column(name="edept",length=20,unique = true)
	   private String dept;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
}
