package com.example.javaPractice.jpaHibernate.basics.springJpaHibernateExample.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.example.javaPractice.jpaHibernate.basics.springJpaHibernateExample.SpringJpaHibernateExampleApplication;
import com.example.javaPractice.jpaHibernate.entity.Course;
import com.example.javaPractice.jpaHibernate.entity.Review;
import com.example.javaPractice.jpaHibernate.repository.CourseRepo;
import com.example.javaPractice.jpaHibernate.repository.CourseSpringDataRepository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.support.Repositories;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(classes = SpringJpaHibernateExampleApplication.class)
public class CourseSpringRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseSpringDataRepository repo;

    @Test
    public void findById_CoursePresent(){
        Optional<Course> courseOptional = repo.findById(10001L); //optional provides if course exist or not
        assertTrue(courseOptional.isPresent());
        logger.info("{}", courseOptional.isPresent());
    }

    @Test
    public void findById_CourseNotPresent(){
        Optional<Course> courseOptional = repo.findById(20001L); //optional provides if course exist or not
        assertTrue(courseOptional.isPresent());
        logger.info("{}", courseOptional.isPresent());
    }

    @Test
    public void SpringRepositorySandBox(){
        /*With the Interface set up we get access to CRUD Methods such as Save/FindAll/Count etc */
        //saving new Course w/ update
        Course course = new Course("MicroServices in 69 steps");
        repo.save(course);
        course.setName("MicroServices in 420 steps");
        repo.save(course);

        //Finding all courses
        logger.info("Courses -> {}", repo.findAll());
        //Number of Courses
        logger.info("Count -> {}", repo.count());


    }

    @Test
    public void SpringRepositorySandBox_Sort(){
        Sort sort = Sort.by("name").descending(); //New way to use sort post Spring Data JPA 2.0 
        //https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.paging-and-sorting
        //can add .and to decending() to add another sort ex Sort.by("name").decending().and(Sort.by("id").accending)
        //Finding all courses but with decending order
        logger.info("Sorted Courses -> {}", repo.findAll(sort));
        //Number of Courses
        logger.info("Count -> {}", repo.count());
        
    }

    @Test
    public void SpringRepositorySandBox_pagination(){ //return a number of results back
        PageRequest pageReq = PageRequest.of(0, 3); // (page, size)
        Page<Course> firstPage = repo.findAll(pageReq);
        logger.info("First Page -> {}",firstPage.getContent());
        Pageable secondPageable = firstPage.nextPageable();
        Page<Course> secondPage = repo.findAll(secondPageable);
        logger.info("Second Page -> {}",secondPage.getContent());
        //Number of Courses
        logger.info("Count -> {}", repo.count());
        
    }

    @Test
    public void findUsingName(){
        logger.info("FindByName -> {}", repo.findByName("Wombology 101"));

    }


}