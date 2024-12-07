package HCQLDemo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class HCQLOperations {

    public static void main(String[] args) {
		HCQLOperations operations = new HCQLOperations();
        operations.addCricketer();
        operations.restrictionsDemo();
        operations.orderDemo();
        operations.aggregateFunctions();
        operations.hqlDemo();
    }

    // Add cricketer using Persistent Object (PO)
    public void addCricketer() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();

        Transaction t = session.beginTransaction();

        Cricketer cricketer = new Cricketer();
        cricketer.setId(106);
        cricketer.setName("BUMRAH");
        cricketer.setGender("MALE");
        cricketer.setAge(35);
        cricketer.setBattingStyle("LEFT-Handed");
        cricketer.setBowlingStyle("RIGHT-ARM FAST");
        cricketer.setEmail("bumrah1@gmail.com");
        cricketer.setContactNumber("8765678945");

        session.persist(cricketer);
        t.commit();
        System.out.println("Cricketer added successfully");
        session.close();
        sf.close();
    }

    public void restrictionsDemo() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Cricketer> cq = cb.createQuery(Cricketer.class);
        Root<Cricketer> root = cq.from(Cricketer.class);

        //cq.select(root).where(cb.lessThan(root.get("age"), 30));//Cricketer with age < 30
        //cq.select(root).where(cb.greaterThan(root.get("age"), 40));//Cricketer with age > 40
        //cq.select(root).where(cb.lessThanOrEqualTo(root.get("age"), 50));//Cricketer with age <= 50
        //cq.select(root).where(cb.greaterThanOrEqualTo(root.get("age"), 40)); //Cricketer with age >= 40
        //cq.select(root).where(cb.notEqual(root.get("name"), "Left-Handed")); //Cricketer with department not equal to "CSE"
        //cq.select(root).where(cb.like(root.get("battingStyle"), "Right%"));//Cricketer with battingStyle starting with "Right"
        //cq.select(root).where(cb.like(root.get("bowlingStyle"), "%Spin")); //Cricketer with battingStyle ending with "Spin"
        //cq.select(root).where(cb.like(root.get("email"), "%email%")); //Cricketer with email containing "email"
        cq.select(root).where(cb.like(root.get("name"), "K__")); //Cricketer with name starting with 'K' and length = 3

        System.out.println("****Cricketer Objects with different comparison criteria****");
        List<Cricketer> cricketers = session.createQuery(cq).getResultList();
        System.out.println("Cricketers Count=" + cricketers.size());
        for (Cricketer c : cricketers) {
            System.out.println(c.toString());
        }

        session.close();
        sf.close();
    }

    public void orderDemo() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Cricketer> cq = cb.createQuery(Cricketer.class);
        Root<Cricketer> root = cq.from(Cricketer.class);

        //Order by age in ascending order
        // cq.orderBy(cb.asc(root.get("age")));
        //Order by name in descending order
        cq.orderBy(cb.desc(root.get("name")));

        System.out.println("****Order By Demo****");
        List<Cricketer> cricketers = session.createQuery(cq).getResultList();
        System.out.println("Cricketers Count=" + cricketers.size());
        for (Cricketer c : cricketers) {
            System.out.println(c.toString());
        }

        session.close();
        sf.close();
    }

    public void aggregateFunctions() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        // Total count of cricketers
        CriteriaQuery<Long> cq1 = cb.createQuery(Long.class);
        Root<Cricketer> root1 = cq1.from(Cricketer.class);
        cq1.select(cb.count(root1));
        long totalCount = session.createQuery(cq1).getSingleResult();
        System.out.println("Total Cricketers Count: " + totalCount);

        // Sum of cricketers' ages
        CriteriaQuery<Double> cq2 = cb.createQuery(Double.class);
        Root<Cricketer> root2 = cq2.from(Cricketer.class);
        cq2.select(cb.sum(root2.get("age")));
        double totalAge = session.createQuery(cq2).getSingleResult();
        System.out.println("Total Cricketers Age: " + totalAge);

        // Average of cricketers' ages
        CriteriaQuery<Double> cq3 = cb.createQuery(Double.class);
        Root<Cricketer> root3 = cq3.from(Cricketer.class);
        cq3.select(cb.avg(root3.get("age")));
        double averageAge = session.createQuery(cq3).getSingleResult();
        System.out.println("Average Cricketers Age: " + averageAge);

        // Minimum cricketer ID
        CriteriaQuery<Integer> cq4 = cb.createQuery(Integer.class);
        Root<Cricketer> root4 = cq4.from(Cricketer.class);
        cq4.select(cb.min(root4.get("id")));
        int minId = session.createQuery(cq4).getSingleResult();
        System.out.println("Minimum Cricketer ID: " + minId);

        // Maximum cricketer ID
        CriteriaQuery<Integer> cq5 = cb.createQuery(Integer.class);
        Root<Cricketer> root5 = cq5.from(Cricketer.class);
        cq5.select(cb.max(root5.get("id")));
        int maxId = session.createQuery(cq5).getSingleResult();
        System.out.println("Maximum Cricketer ID: " + maxId);

        // Distinct count of batting styles
        CriteriaQuery<Long> cq6 = cb.createQuery(Long.class);
        Root<Cricketer> root6 = cq6.from(Cricketer.class);
        cq6.select(cb.countDistinct(root6.get("battingStyle")));
        long distinctCount = session.createQuery(cq6).getSingleResult();
        System.out.println("Distinct Batting Styles Count: " + distinctCount);

        session.close();
        sf.close();
    }

    // Display cricketers from a specific department based on age in ascending order
    public void hqlDemo() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Cricketer> cq = cb.createQuery(Cricketer.class);
        Root<Cricketer> root = cq.from(Cricketer.class);

        cq.select(root).where(cb.equal(root.get("name"), "Left-Handed"));
        cq.orderBy(cb.asc(root.get("age")));

        List<Cricketer> cricketers = session.createQuery(cq).getResultList();
        System.out.println("Cricketers Count=" + cricketers.size());
        for (Cricketer c : cricketers) {
            System.out.println(c.toString());
        }

        session.close();
        sf.close();
    }
}
