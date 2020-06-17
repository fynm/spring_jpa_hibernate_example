package com.example.javaPractice.jpaHibernate.basics.springJpaHibernateExample.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.javaPractice.jpaHibernate.basics.springJpaHibernateExample.SpringJpaHibernateExampleApplication;
import com.example.javaPractice.jpaHibernate.entity.Course;
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
    
}