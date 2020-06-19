package com.example.javaPractice.jpaHibernate.basics.springJpaHibernateExample.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.example.javaPractice.jpaHibernate.basics.springJpaHibernateExample.SpringJpaHibernateExampleApplication;
import com.example.javaPractice.jpaHibernate.entity.Course;
import com.example.javaPractice.jpaHibernate.entity.Passport;
import com.example.javaPractice.jpaHibernate.entity.Student;
import com.example.javaPractice.jpaHibernate.repository.CourseRepo;
import com.example.javaPractice.jpaHibernate.repository.StudentRepo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(classes = SpringJpaHibernateExampleApplication.class)
public class StudentRepoTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentRepo repo;

    @Autowired
    EntityManager em;

    @Test
    @Transactional //Persistence Context -> all operations are being stored in the Persistence Context
    public void someTest(){
        //Database Operation 1 - Retrieve Student
        Student student = em.find(Student.class, 20002L);
        //Persistence Context(student)

        //Database Operation 2 - Retrieve Passport
        Passport passport = student.getPassport();
        //Persistence Context(student, passport)

        //Database Operation 3 - Update Passport
        passport.setNumber("E3215135");
        //Persistence Context(student, passport++)

        //Database Operation 4 - Update Student
        student.setName("Yonica Man");
        //Persistence Context(student++, passport++)
    }


    @Test
    @Transactional
    public void findStudentandPassport(){
       Student student = em.find(Student.class, 20002L);
       logger.info("Student -> {}", student);
       logger.info("passport -> {}", student.getPassport());
       /*Note
        1to1 relationships are ALWAYS eager retrieve which means that when student
        is retrieved, so is the passport! -> called "Eagar Fetching"
        PT2
        Now that ive set passport in student to LAZY, it will only call the passport when we do a getPassport
        This gives move overhead when calling getPassport but not when only calling student
       */
    }

    @Test
    @Transactional
    public void findPassportandStudent(){
       Passport passport = em.find(Passport.class, 30001L);
       logger.info("Passport -> {}", passport);
       logger.info("Student -> {}", passport.getStudent());
       /*This example shows off bi-directional 1to1 relationship
            We can call student from passport but on the table
            passport does not have the student_id but student
            has the passport_id but we can still get student from
            calling passport first because it is "mappedBy" */
    }

    @Test
    @Transactional
    public void retrieveStudentAndCourses(){
        Student student = em.find(Student.class, 20001L);
        logger.info("Student -> {}",student);
        logger.info("Student courses -> {}",student.getCourses());
    }
    
}