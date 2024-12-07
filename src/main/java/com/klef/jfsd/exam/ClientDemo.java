package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo 
{
  public static void main(String args[])
  {
	  Configuration cfg = new Configuration();
  	  cfg.configure("hibernate.cfg.xml");
  	
  	  SessionFactory sf = cfg.buildSessionFactory();
  	  Session session = sf.openSession();
  	
  	  Transaction t = session.beginTransaction();
  	  
  	  Employee e = new Employee();

  	  e.setName("SURYA");

  	  e.setName("durga");
  	  e.setSalary("939478");
  	  e.setDept("CSE");
  	  
  	  Instructor i = new Instructor();
  	  i.setEmail("Tej@gmail.com");
  	  i.setName("Tej");
  	  i.setPhoneno("8767687647");
 
  	  
  	  Course s = new Course();
  	  s.setName("JFSD");
  	  s.setCredits("4");

  	  session.persist(e);
  	  session.persist(i);
  	 session.persist(s);
  	 
  	   t.commit();
  	   System.out.println("SUCCESS....");
  	
  	session.close();
  	sf.close();
  }
}
