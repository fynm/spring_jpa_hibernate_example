package com.example.javaPractice.jpaHibernate.basics.springJpaHibernateExample.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
public class JpqlTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    public void test_select_query_basic(){
        //List results = em.createQuery("Select c From Course c").getResultList(); 
        List results = em.createNamedQuery("query_get_all_courses", Course.class).getResultList(); 
        //since we created a named query in Course, we can use this method and call the query instead of manually typing the query
        logger.info("Select c from Course c -> {}", results);
    }

    @Test
    public void test_select_query_where(){
        //List results = em.createQuery("Select c From Course c where name like 'Wombology%'").getResultList(); //query_get_all_courses_wombo
        List results = em.createNamedQuery("query_get_all_courses_wombo", Course.class).getResultList();
        logger.info("Select c from Course c where name like '%Wombology '-> {}", results);
    }

    @Test
    public void test_query_courseWithoutStudents(){
        TypedQuery<Course> query = em.createQuery("select c from Course c where c.students is empty ", Course.class);
        List<Course> results = query.getResultList();
        logger.info("Courses without students -> {}", results);
    }

    @Test
    public void test_query_courseWithMorethan2Students(){
        TypedQuery<Course> query = em.createQuery("select c from Course c where size(c.students) >= 2 ", Course.class);
        List<Course> results = query.getResultList();
        logger.info("Courses with 2+ students -> {}", results);
    }
    
    @Test
    public void test_query_orderBySizeOfStudents(){
        TypedQuery<Course> query = em.createQuery("select c from Course c order by size(c.students) desc", Course.class); //can add desc for descending
        List<Course> results = query.getResultList();
        logger.info("Courses decending -> {}", results);
    }

    @Test
    public void test_query_studentwithpassportWithPattern(){
        TypedQuery<Student> query = em.createQuery("select s from Student s where s.passport.number like 'E1%'", Student.class); //can add desc for descending
        List<Student> results = query.getResultList();
        logger.info("Student with Passport E1  -> {}", results);
    }

    //JBQL Additions to note
    //like
    //BETWEEN 100 and 1000
    //IS NULL
    //upper, lower, trim, length etc

    //JOIN -> select s, c from Student s join s.courses c ---OR--- select c, s from Course c join c.students s
    //LEFT JOIN => select s, c from Student s left join s.courses c ---OR--- select c, s from Course c left join c.students s
    //CROSS JOIN => takes every course with every student -> select c,s from Course c, Student s
    @Test
    public void join(){
        Query query = em.createQuery("select c, s from Course c JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Result Size -> {} ", resultList.size());
        for(Object[] result:resultList){
            logger.info("course: {} Student: {}", result[0], result[1]);
        }
    }

    @Test
    public void left_join(){
        Query query = em.createQuery("select c, s from Course c LEFT JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Result Size -> {} ", resultList.size());
        for(Object[] result:resultList){
            logger.info("course: {} Student: {}", result[0], result[1]);
        }
    }

    @Test
    public void cross_join(){
        Query query = em.createQuery("select c, s from Course c, Student s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Result Size -> {} ", resultList.size());
        for(Object[] result:resultList){
            logger.info("course: {} Student: {}", result[0], result[1]);
        }
    }



}