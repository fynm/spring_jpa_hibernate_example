package com.example.javaPractice.jpaHibernate.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

//Note this is a hibernate function, this is now allowed if you choose to use JPA with another framework that is not hibernate
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/*Declares this as an Entity Bean */
@Entity
//@NamedQuery(name="query_get_all_courses", query="Select c From Course c") //However we only can use this @ once, use @NamedQueries when theres multiple Queries

// @Table(name="Student_details") //User this to rename the table when created or
// sync it to the specific table name that exists
public class Review {
    @Id // Sets it as a Primary Key
    @GeneratedValue // Will create an increasing Id for each id made
    private Long id;

    @Column(nullable = false) // will set database column name to fullname or sync it to fullname,
                              // nullable will make sure it will never be null -> will throw an error
                              // if a null is inserted
    private String description;
    // Other column options are like unique=true where column can only have one item
    // of that specific name else it will throw an error if it already exists
    // length - set length of name

    private int rating;

    @UpdateTimestamp //Note this is a hibernate function, this is now allowed if you choose to use JPA with another framework that is not hibernate
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp //Note this is a hibernate function, this is now allowed if you choose to use JPA with another framework that is not hibernate
    private LocalDateTime createdDate;

    @ManyToOne //There are many reviews to a single course
    private Course course;

    //Default Constructor -> Needed by JPA
    public Review(){}

    //Constructor
    public Review(String description, int rating){
        this.description = description;
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Review [course=" + course + ", description=" + description + ", id=" + id + ", rating=" + rating + "]";
    }

    



}