package com.example.javaPractice.jpaHibernate.basics.springJpaHibernateExample.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

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
public class NativeQueriesTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    public void native_queries_basic(){
        Query query = em.createNativeQuery("SELECT * FROM course where id = ?", Course.class);
        query.setParameter(1, 10001L);
        List<Query> result = query.getResultList();
        logger.info("Select * from course where id = ? -> {}", result);
    }
    
    @Test
    @Transactional
    public void native_queries_update(){
        Query query = em.createNativeQuery("update course set last_updated_date=sysdate() ", Course.class);
        int noOfRowsUpdated = query.executeUpdate();
        logger.info("Number of Rows Updated -> {}", noOfRowsUpdated);
    }

}