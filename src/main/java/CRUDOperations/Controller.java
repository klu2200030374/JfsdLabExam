package CRUDOperations;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Controller {

    public static void main(String[] args) {
        Controller controller = new Controller();
        //controller.addDirector();
        //controller.displayDirectorById();
        controller.updateDirector();
        //controller.deleteDirector();
    }

    public void addDirector() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();

        Transaction t = session.beginTransaction();

        Director director = new Director();
        director.setId(103);
        director.setName("NAGA ASWIN");
        director.setGender("MALE");
        director.setDepartment("MOVIE DIRECTOR");
        director.setSalary(120000);
        director.setContactNo("9440970903");
        director.setExperience(10);  

        session.persist(director);
        t.commit();

        System.out.println("Director Added Successfully");

        session.close();
        sf.close();
    }
    public void displayDirectorById() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Director ID:");
        int did = sc.nextInt();

        Director director = session.find(Director.class, did);
        if (director != null) {
            System.out.println("Director ID: " + director.getId());
            System.out.println("Director Name: " + director.getName());
            System.out.println("Director Gender: " + director.getGender());
            System.out.println("Director Department: " + director.getDepartment());
            System.out.println("Director Salary: " + director.getSalary());
            System.out.println("Director Contact No: " + director.getContactNo());
            System.out.println("Director Experience: " + director.getExperience());  
        } else {
            System.out.println("Director ID Not Found");
        }
        sc.close();
        session.close();
        sf.close();
    }

    public void updateDirector() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();

        Transaction t = session.beginTransaction();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Director ID:");
        int did = sc.nextInt();

        Director director = session.find(Director.class, did);
        if (director != null) {
            System.out.println("Enter Director Name:");
            String dname = sc.next();
            System.out.println("Enter Director Salary:");
            double dsalary = sc.nextDouble();
            System.out.println("Enter Director Contact:");
            String dcontactno = sc.next();
            System.out.println("Enter Director Experience:");
            int dexperience = sc.nextInt();  

            director.setName(dname);
            director.setSalary(dsalary);
            director.setContactNo(dcontactno);
            director.setExperience(dexperience);  

            t.commit();
            System.out.println("Director Updated Successfully");
        } else {
            System.out.println("Director ID Not Found");
        }
        sc.close();
        session.close();
        sf.close();
    }

    public void deleteDirector() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();

        Transaction t = session.beginTransaction();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Director ID:");
        int did = sc.nextInt();

        Director director = session.find(Director.class, did);
        if (director != null) {
            session.remove(director);
            t.commit();
            System.out.println("Director Deleted Successfully");
        } else {
            System.out.println("Director ID Not Found");
        }
        sc.close();
        session.close();
        sf.close();
    }
}
