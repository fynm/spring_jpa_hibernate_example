package com.example.javaPractice.jpaHibernate.basics.springJpaHibernateExample.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
public class CourseRepoTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepo repo;

    @Test
    public void findById_basic(){
        Course course = repo.findById(10001L);
        logger.info("Test is running");
        assertEquals("JPA in 50 steps", course.getName());
        //assertEquals("JPA in 500 steps", course.getName());
    }

    @Test
    @DirtiesContext //With this tag spring will reset the data so the delete doesnt actually delete
    public void deleteById_basic(){
        repo.deleteById(10001L);
        assertNull(repo.findById(10001L));
    }

    @Test
    @DirtiesContext
    public void save_basic(){
        //update course
        Course course = repo.findById(10001L);
        course.setName("New Course Name!");
        repo.save(course);
        //check that it has updated
        Course course1 = repo.findById(10001L);
        assertEquals("New Course Name!", course1.getName());

        //save course
        repo.save(new Course("Test Course"));
        Course course2 = repo.findById(2L);
        assertEquals("Test Course", course2.getName());
        
    }
}