package com.example.javaPractice.jpaHibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.javaPractice.jpaHibernate.entity.Course;
import com.example.javaPractice.jpaHibernate.entity.Review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CourseRepo {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    //find Course by Id
    public Course findById(Long id){
        return em.find(Course.class, id);
    }
    //Course Save -> insert AND update
    public Course save(Course course){
        if(course.getId() == null){ 
            em.persist(course); //insert
        }else{
            em.merge(course); //update
        }

        return course;
    }

    //Course Delete
    public void deleteById(Long id){
        Course course = findById(id);
        em.remove(course);
    }

    public void addReview(long courseId, List<Review> reviews){
        Course course = findById(courseId);
        for(Review review:reviews){
            course.addReviews(review);  //add reivew to the course
            review.setCourse(course);   //sets the course id in review
            em.persist(review);         //add the new review to the DB
        }
    }

    public void addReviewsForCourse(){
        //get course 10003 - hardcoded for this example
        Course course = findById(10003L);
        logger.info("course.getReviews -> {}", course.getReviews());
        //add two reviews to it
        Review review = new Review("This sucks", 1);
        course.addReviews(review);
        review.setCourse(course); //need to set both relationships!
        Review review2 = new Review("This really could have been better", 2);
        course.addReviews(review2);
        review2.setCourse(course);//need to set both relationships!
        //save it to the database
        em.persist(review);
        em.persist(review2);
        em.flush();
        logger.info("course.getReviews -> {}", course.getReviews());
    }

    public void playWithEnitityManager(){
        Course course = new Course("This is the OG Course Name!");
        em.persist(course);
        course.setName("This is the Updated Course Name!");
        /*Even though we set the name AFTER the persist call to upload the new course
            due to @Transactional, it will continue updating the database item when you update the object
            as shown here.
         */
        em.flush(); //Will push the current changes to the database
        em.detach(course); //will stop saving changes happening to course
        //OR you can use clear
        em.clear(); //will clear all values and will not get saved to the DB
        course.setName("This will not get saved!");

        Course course1 = new Course("Testing our Refresh!");
        em.persist(course1);
        em.flush();

        course1.setName("Will this Refresh?");
        em.refresh(course1); //The refresh will restore the data to the data from the database, getting rid of the changes i made in the line above
        /*Checking the DB we see that the new name is not set and is the original name when i inserted it with course1 */
        em.flush();

    }

    /*JPQL - Queries with entities -> gets converted to SQL queries at runtime
        SQL: select * from course
        JPQL: select c from course c
    
    */



}