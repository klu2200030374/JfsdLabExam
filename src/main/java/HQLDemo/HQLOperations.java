package HQLDemo;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

public class HQLOperations 
{
  public static void main(String[] args)
  {
     HQLOperations operations = new HQLOperations();
     //operations.addMovie();
     //operations.displayAllMoviesCompleteObj();
     //operations.displayAllMoviesPartialObj();
     operations.aggregateFunctions();
     operations.updatePositionalParams();
     operations.updateNamedParams();
     operations.deletePositionalParams();
     operations.deleteNamedParams();
     operations.displayMovieByIdPositionalParams();
     operations.displayMovieByIdPositionalParams();
     operations.displayMovies();
  }
   
  // adding movie using persistent object(PO)
  public void addMovie()
  {
    Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        
        Transaction t = session.beginTransaction();
        
        Movies movie = new Movies();
        movie.setId(5);
        movie.setTitle("DEVARA");
        movie.setDirector("KORATALA SHIVA");
        movie.setGenre("TRILLER");
        movie.setReleaseYear(2024);
        movie.setRating(7.1);
        movie.setAvailable(true);
        
        session.persist(movie); // movie is a persistent object, using this object we will perform operations
        t.commit();
        System.out.println("Movie Added Successfully");
        
        session.close();
        sf.close();
  }
  
  public void displayAllMoviesCompleteObj() // complete object
  {
    Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        
        String hql = "from Movies"; // select * from movie_table
        Query<Movies> qry = session.createQuery(hql, Movies.class);
        List<Movies> movieList = qry.getResultList();
        System.out.println("Total Movies=" + movieList.size());
        
        for(Movies m : movieList)
        {
          System.out.println("ID:" + m.getId());
          System.out.println("Title:" + m.getTitle());
          System.out.println("Director:" + m.getDirector());
          System.out.println("Genre:" + m.getGenre());
          System.out.println("Release Year:" + m.getReleaseYear());
          System.out.println("Rating:" + m.getRating());
          System.out.println("Available:" + m.isAvailable());          
        }
        session.close();
        sf.close();
        
  }
  
  public void displayAllMoviesPartialObj() // partial object
  {
    Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        
        String hql = "select m.id, m.title, m.rating from Movies m";
        // m is a reference object (OR) alias
        
        Query<Object[]> qry = session.createQuery(hql, Object[].class);
        List<Object[]> movieList = qry.getResultList();
        
        System.out.println("Total Movies=" + movieList.size());
        for(Object[] obj : movieList)
        {
          System.out.println("Movie ID:" + obj[0]);
          System.out.println("Movie Title:" + obj[1]);
          System.out.println("Movie Rating:" + obj[2]);
        }
        session.close();
        sf.close();
  }
  
  public void aggregateFunctions()
  {
      Configuration configuration = new Configuration();
      configuration.configure("hibernate.cfg.xml");
      
      SessionFactory sf = configuration.buildSessionFactory();
      Session session = sf.openSession();
      
      String hql1 = "select count(*) from Movies";
      // you can also use count(property)
      Query<Long> qry1 = session.createQuery(hql1, Long.class);
      Long count = qry1.getSingleResult();    
      System.out.println("Total Movies=" + count);
      
      String hql2 = "select avg(rating) from Movies";
      Query<Double> qry2 = session.createQuery(hql2, Double.class);
      Double avgRating = qry2.getSingleResult();    
      System.out.println("Average Rating=" + avgRating);
      
      String hql3 = "select min(releaseYear) from Movies";
      Query<Integer> qry3 = session.createQuery(hql3, Integer.class);
      Integer minYear = qry3.getSingleResult();    
      System.out.println("Earliest Release Year=" + minYear);
      
      String hql4 = "select max(releaseYear) from Movies";
      Query<Integer> qry4 = session.createQuery(hql4, Integer.class);
      Integer maxYear = qry4.getSingleResult();    
      System.out.println("Latest Release Year=" + maxYear);
      
      session.close();
      sf.close();
  }
  
  public void updatePositionalParams()
  {
      Configuration configuration = new Configuration();
      configuration.configure("hibernate.cfg.xml");
      
      SessionFactory sf = configuration.buildSessionFactory();
      Session session = sf.openSession();
      
      Transaction t = session.beginTransaction();
      
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter Movie ID:");
      int mid = sc.nextInt();
      System.out.println("Enter Movie Title:");
      String mtitle = sc.next();
      System.out.println("Enter Movie Rating:");
      double mrating = sc.nextDouble();
      
      String hql = "update Movies set title=?1, rating=?2 where id=?3";
      MutationQuery qry = session.createMutationQuery(hql);
      qry.setParameter(1, mtitle);
      qry.setParameter(2, mrating);
      qry.setParameter(3, mid);
      int n = qry.executeUpdate(); 
      
      t.commit();
      System.out.println(n + " Movie(s) Updated Successfully");
      
      sc.close();
      session.close();
      sf.close();
  }
  
  public void updateNamedParams()
  {
      Configuration configuration = new Configuration();
      configuration.configure("hibernate.cfg.xml");
      
      SessionFactory sf = configuration.buildSessionFactory();
      Session session = sf.openSession();
      
      Transaction t = session.beginTransaction();
      
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter Movie ID:");
      int mid = sc.nextInt();
      System.out.println("Enter Movie Title:");
      String mtitle = sc.next();
      System.out.println("Enter Movie Rating:");
      double mrating = sc.nextDouble();
      
      String hql = "update Movies set title=:v1, rating=:v2 where id=:v3";
      MutationQuery qry = session.createMutationQuery(hql);
      qry.setParameter("v1", mtitle);
      qry.setParameter("v2", mrating);
      qry.setParameter("v3", mid);
      int n = qry.executeUpdate(); 
      
      t.commit();
      System.out.println(n + " Movie(s) Updated Successfully");
      
      sc.close();
      session.close();
      sf.close();
    
  }
  
  public void deletePositionalParams()
  {
      Configuration configuration = new Configuration();
      configuration.configure("hibernate.cfg.xml");
      
      SessionFactory sf = configuration.buildSessionFactory();
      Session session = sf.openSession();
      
      Transaction t = session.beginTransaction();
      
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter Movie ID:");
      int mid = sc.nextInt();
      
      String hql = "delete from Movies where id=?1";
      MutationQuery qry = session.createMutationQuery(hql);
      qry.setParameter(1, mid);
      int n = qry.executeUpdate(); 
      
      t.commit();
     
      if (n > 0) {
          System.out.println("Movie Deleted Successfully");
      } else {
          System.out.println("Movie ID Not Found");
      }
      
      sc.close();
      session.close();
      sf.close();
  }
  
  public void deleteNamedParams()
  {
      Configuration configuration = new Configuration();
      configuration.configure("hibernate.cfg.xml");
      
      SessionFactory sf = configuration.buildSessionFactory();
      Session session = sf.openSession();
      
      Transaction t = session.beginTransaction();
      
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter Movie ID:");
      int mid = sc.nextInt();
      
      String hql = "delete from Movies where id=:v";
      MutationQuery qry = session.createMutationQuery(hql);
      qry.setParameter("v", mid);
      int n = qry.executeUpdate(); 
      
      t.commit();
     
      if (n > 0) {
          System.out.println("Movie Deleted Successfully");
      } else {
          System.out.println("Movie ID Not Found");
      }
      
      sc.close();
      session.close();
      sf.close();
  }
  
  // Display movie by id using positional params
  public void displayMovieByIdPositionalParams()
  {
      Configuration configuration = new Configuration();
      configuration.configure("hibernate.cfg.xml");
      
      SessionFactory sf = configuration.buildSessionFactory();
      Session session = sf.openSession();
      
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter Movie ID:");
      int mid = sc.nextInt();
      
      String hql = "from Movies where id=:v"; 
      Query<Movies> qry = session.createQuery(hql, Movies.class);
      qry.setParameter("v", mid);
      
      Movies m = qry.getSingleResultOrNull();
      
      if (m != null)
      {
          // We can use getter methods to print every property of Movies object(m) 
          System.out.println(m.toString());
      }
      else {
          System.out.println("Movie ID Not Found");
      }
      sc.close();
      session.close();
      sf.close();
  }
  
  // Display movies based on genre and rating
  public void displayMovies()
  {
      Configuration configuration = new Configuration();
      configuration.configure("hibernate.cfg.xml");
      
      SessionFactory sf = configuration.buildSessionFactory();
      Session session = sf.openSession();
      
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter Movie Genre:");
      String genre = sc.next();
      System.out.println("Enter Minimum Rating:");
      double rating = sc.nextDouble();
      
      String hql = "from Movies where genre=?1 and rating>=?2";
      Query<Movies> qry = session.createQuery(hql, Movies.class);
      qry.setParameter(1, genre);
      qry.setParameter(2, rating);
      List<Movies> movieList = qry.getResultList();
      System.out.println("Total Movies=" + movieList.size());
      
      for(Movies m : movieList)
      {
        System.out.println("ID:" + m.getId());
        System.out.println("Title:" + m.getTitle());
        System.out.println("Director:" + m.getDirector());
        System.out.println("Genre:" + m.getGenre());
        System.out.println("Release Year:" + m.getReleaseYear());
        System.out.println("Rating:" + m.getRating());
        System.out.println("Available:" + m.isAvailable());          
      }
      session.close();
      sf.close();
  }
}
