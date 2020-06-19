package com.example.javaPractice.jpaHibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.javaPractice.jpaHibernate.entity.Course;
import com.example.javaPractice.jpaHibernate.entity.Employee;
import com.example.javaPractice.jpaHibernate.entity.FullTimeEmployee;
import com.example.javaPractice.jpaHibernate.entity.PartTimeEmployee;
import com.example.javaPractice.jpaHibernate.entity.Review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EmployeeRepo {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    public Employee findById(Long id){
        return em.find(Employee.class, id);
    }

    //insert employee
    public void insert(Employee employee){
        em.persist(employee);
    }

    //retrieve all employees -> Commented out for MappedSupperClass Implementation because Employee is not mapped, only Part and Full
    // public List<Employee> retrieveAllEmployees(){
    //     return em.createQuery("select e from Employee e", Employee.class).getResultList();
    // }

    public List<PartTimeEmployee> retrieveAllPartTimeEmployees(){
        return em.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class).getResultList();
    }

    public List<FullTimeEmployee> retrieveAllFullTimeEmployees(){
        return em.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class).getResultList();
    }
   

}