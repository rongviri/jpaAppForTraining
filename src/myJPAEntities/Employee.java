/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myJPAEntities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author CSTURoom111
 */
@Entity
@Table(name="T_EMPLOYEE")
@NamedQuery(name="updateName", query="UPDATE Employee as e SET e.name=:name WHERE e.emp_id=:id")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long emp_id;

    @Column(name="NAME", nullable = false, length = 200)
    private String name;

    
    @OneToOne
    @JoinColumn(name = "FK_COMPANY_ID")
    private Company workingCompany;

    
    @OneToOne
    @JoinColumn(name="FK_MANAGER")
    private Employee manager ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getWorkingCompany() {
        return workingCompany;
    }

    public void setWorkingCompany(Company workingCompany) {
        this.workingCompany = workingCompany;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
    
    public Long getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Long emp_id) {
        this.emp_id = emp_id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emp_id != null ? emp_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the emp_id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.emp_id == null && other.emp_id != null) || (this.emp_id != null && !this.emp_id.equals(other.emp_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "myJPAEntities.Employee[ id=" + emp_id + " ]";
    }
    public static void main(String[] args) {
        EntityManagerFactory  emf = Persistence.createEntityManagerFactory("PU1");
        EntityManager em = emf.createEntityManager();
        Employee emp = new Employee();
        emp.setName("Nayada");
        try {
            em.getTransaction().begin();
            em.persist(emp);
            em.flush();
            
            Query query = em.createNamedQuery("updateName");
            query.setParameter("id", new Long(1));
            query.setParameter("name", new String("Mr. Nui"));
            int rowCounted = query.executeUpdate();
            System.out.println("Number of Affected Rows:" + rowCounted);
            em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            em.close();
            emf.close();
        }
        
    }
    
}
