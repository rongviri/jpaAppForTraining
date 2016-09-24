/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myJPAEntities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.AccessType;
import javax.persistence.OneToMany;

/**
 *
 * @author CSTURoom111
 */
@Entity
@Table(name="T_COMPANY")
@Access(AccessType.FIELD)
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long company_id;
    
    @Column(nullable = false, length = 100, name = "NAME")
    private String name ;
    
    @OneToMany(mappedBy = "workingCompany")
    private Set<Employee> employees ;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Long company_id) {
        this.company_id = company_id;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee){
        if(this.employees != null){
            if(!employees.contains(employee))
                employees.add(employee);
        }
        else{
            employees = new HashSet<Employee>();
            employees.add(employee);
        }    
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (company_id != null ? company_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.company_id == null && other.company_id != null) 
                || (this.company_id != null && !this.company_id.equals(other.company_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "myJPAEntities.Company[ id=" + company_id + " ]";
    }
    
}
