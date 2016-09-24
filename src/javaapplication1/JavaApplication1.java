/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import myJPAEntities.Book;
import myJPAEntities.Company;
import myJPAEntities.Employee;
import myJPAEntities.Person;

/**
 *
 * @author CSTURoom111
 */
public class JavaApplication1 {

    private EntityManagerFactory emf;
    private EntityManager em;

    public JavaApplication1() {
        emf = Persistence.createEntityManagerFactory("PU1");
        em = emf.createEntityManager();
    }

    public EntityManager getEm() {
        return em;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        JavaApplication1 app = new JavaApplication1();

        Employee e1 = new Employee();
        e1.setName("Nayada");

        Employee e2 = new Employee();
        e2.setName("Patcharat");

        Company c1 = new Company();
        c1.setName("Thammasat University");

        app.getEm().getTransaction().begin();
        try {
            app.getEm().persist(c1);
            app.getEm().persist(e1);
            app.getEm().persist(e2);
            e1.setWorkingCompany(c1);
            e2.setWorkingCompany(c1);
            c1.addEmployee(e1);
            c1.addEmployee(e2);

            //app.getEm().flush();
            app.getEm().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            app.getEm().getTransaction().rollback();
        }

        Company c = app.getEm().find(Company.class, new Long(1));
        if (c != null) {
            for (Employee e : c.getEmployees()) {
                app.getEm().getTransaction().begin();
                try {
                    e.setName("Khun " + e.getName());
                    app.getEm().getTransaction().commit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    app.getEm().getTransaction().rollback();
                }
            }
        }
        c = app.getEm().find(Company.class, new Long(1));
        System.out.println("The Staff of " + c.getName() + "as follows :");
        if (c != null) {
            for (Employee e : c.getEmployees()) {
                System.out.println(e.getEmp_id() + ":" + e.getName() +":"+e.getWorkingCompany().getName() );
            }
        }
        app.getEm().close();
        app.getEmf().close();

//        Person p1 = new Person();
//        p1.setFirstName("Songsakdi");
//        p1.setLastName("Rongviriyapanish");
//        
//        JavaApplication1 app1 = new JavaApplication1();    
//        Book b1 = new Book("title", new Float(10.25), "description", "isbn", new Integer("1"), Boolean.TRUE);
//        app1.persist(b1);
//        
//        Employee emp1 = new Employee();
//        emp1.setName("Songsakdi Rongviriyapanish");
//        app1.persist(emp1);
//        
//        Company comp1 = new Company();
//        comp1.setName("Thammasat University");
//        app1.persist(comp1);
//        
//        emp1.setWorkingCompany(comp1);
//        app1.persist(emp1);
//        
//        System.out.println("ID:" +emp1.getEmp_id());
//        System.out.println("Name:"+emp1.getName());
//        System.out.println("Company ID:"+emp1.getWorkingCompany().getCompany_id());
//        System.out.println("Company:"+emp1.getWorkingCompany().getName());
//        app1.getEm().close();
//        app1.getEmf().close();
//        
//        Person p2 = em.find(Person.class, new Long("2"));
//        System.out.println(p2.getFirstName());
//        System.out.println(p2.getLastName());
//        System.out.println(p2.getId());
    }

    public void persist(Object object) {
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            //em.close();
        }
    }

}
