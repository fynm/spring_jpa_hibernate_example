package com.example.javaPractice.jpaHibernate.basics.springJpaHibernateExample.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.example.javaPractice.jpaHibernate.basics.springJpaHibernateExample.SpringJpaHibernateExampleApplication;
import com.example.javaPractice.jpaHibernate.entity.Course;
import com.example.javaPractice.jpaHibernate.entity.Student;
import com.example.javaPractice.jpaHibernate.repository.CourseRepo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(classes = SpringJpaHibernateExampleApplication.class)
public class CriteriaQueryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    public void criteriaQuery_basic(){
        /*
        Select c from Course c -> JPQL

        Steps for a Criteria Query
        1. Use Criteria Builder to create a Criteria Query returnning the expected result object */

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query

        Root<Course> courseRoot = cq.from(Course.class);

        //3. Define Predicates etc using Criteria Builder

        //4. Add Predicates etc to the Criteria Query

        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> results = query.getResultList(); 
        logger.info("Typed Query-> {}", results);
    }


    @Test
    public void criteriaQuery_where500Steps(){
        /*
        Select c from Course c where name like '%500%'

        Steps for a Criteria Query
        1. Use Criteria Builder to create a Criteria Query returnning the expected result object */

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query

        Root<Course> courseRoot = cq.from(Course.class);

        //3. Define Predicates etc using Criteria Builder
        Predicate like500 = cb.like(courseRoot.get("name"), "%500%");

        //4. Add Predicates etc to the Criteria Query
        cq.where(like500);

        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> results = query.getResultList(); 
        logger.info("Typed Query-> {}", results);
    }

    @Test
    public void criteriaQuery_allCoursesWithoutStudents(){
        /*
        Select c from Course c where c.students is empty

        Steps for a Criteria Query
        1. Use Criteria Builder to create a Criteria Query returnning the expected result object */

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query

        Root<Course> courseRoot = cq.from(Course.class);

        //3. Define Predicates etc using Criteria Builder
        Predicate emptyStudent = cb.isEmpty(courseRoot.get("students"));

        //4. Add Predicates etc to the Criteria Query
        cq.where(emptyStudent);

        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> results = query.getResultList(); 
        logger.info("Typed Query-> {}", results);
    }


    @Test
    public void criteriaQuery_joins(){
        /*
        Select c from Course c where c.students is empty

        Steps for a Criteria Query
        1. Use Criteria Builder to create a Criteria Query returnning the expected result object */

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query

        Root<Course> courseRoot = cq.from(Course.class);

        //3. Define Predicates etc using Criteria Builder
        Join<Object, Object> join = courseRoot.join("students");

        //4. Add Predicates etc to the Criteria Query

        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> results = query.getResultList(); 
        logger.info("Typed Query-> {}", results);
    }

    @Test
    public void criteriaQuery_left_joins(){
        /*
        Select c from Course c where c.students is empty

        Steps for a Criteria Query
        1. Use Criteria Builder to create a Criteria Query returnning the expected result object */

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query

        Root<Course> courseRoot = cq.from(Course.class);

        //3. Define Predicates etc using Criteria Builder
        Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);

        //4. Add Predicates etc to the Criteria Query

        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> results = query.getResultList(); 
        logger.info("Typed Query-> {}", results);
    }


    //JBQL Additions to note
    //like
    //BETWEEN 100 and 1000
    //IS NULL
    //upper, lower, trim, length etc

    //JOIN -> select s, c from Student s join s.courses c ---OR--- select c, s from Course c join c.students s
    //LEFT JOIN => select s, c from Student s left join s.courses c ---OR--- select c, s from Course c left join c.students s
    //CROSS JOIN => takes every course with every student -> select c,s from Course c, Student s
    
}