package com.example.javaPractice.jpaHibernate.repository;

import javax.persistence.EntityManager;

import com.example.javaPractice.jpaHibernate.entity.Course;
import com.example.javaPractice.jpaHibernate.entity.Passport;
import com.example.javaPractice.jpaHibernate.entity.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class StudentRepo {

    @Autowired
    EntityManager em;

    //find Student by Id
    public Student findById(Long id){
        return em.find(Student.class, id);
    }
    //Student Save -> insert AND update
    public Student save(Student student){
        if(student.getId() == null){ 
            em.persist(student); //insert
        }else{
            em.merge(student); //update
        }

        return student;
    }

    //Student Delete
    public void deleteById(Long id){
        Student student = findById(id);
        em.remove(student);
    }

    public void saveStudentWithPassport(){
        Passport passport = new Passport("L2363247");
        em.persist(passport);   //need to push the passport to the database else the student cannot grab this entry
        Student student = new Student("Mike");
        student.setPassport(passport); //student is the owning side of this 1to1 relationship, must be created after passport
        em.persist(student);
    }

    public void insertStudentAndCourse(){
        Student student = new Student("Jack");
        Course course = new Course("Super cool course");
        em.persist(student);
        em.persist(course);
        student.addCourse(course);
        course.addStudent(student);
        em.persist(student);
        
    }

    public void insertStudentAndCourse(Student student, Course course){
        em.persist(student);
        em.persist(course);
        student.addCourse(course);
        course.addStudent(student);
        em.persist(student);
        
    }



}