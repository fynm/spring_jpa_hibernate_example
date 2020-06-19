package com.example.javaPractice.jpaHibernate.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//Note this is a hibernate function, this is now allowed if you choose to use JPA with another framework that is not hibernate
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/*Declares this as an Entity Bean */
@Entity

// @Table(name="Student_details") //User this to rename the table when created or
// sync it to the specific table name that exists
public class Student {
    @Id // Sets it as a Primary Key
    @GeneratedValue // Will create an increasing Id for each id made
    private Long id;

    @Column(nullable = false) // will set database column name to fullname or sync it to fullname,
                              // nullable will make sure it will never be null -> will throw an error
                              // if a null is inserted
    private String name;
    // Other column options are like unique=true where column can only have one item
    // of that specific name else it will throw an error if it already exists
    // length - set length of name

    @UpdateTimestamp //Note this is a hibernate function, this is now allowed if you choose to use JPA with another framework that is not hibernate
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp //Note this is a hibernate function, this is now allowed if you choose to use JPA with another framework that is not hibernate
    private LocalDateTime createdDate;

    @OneToOne(fetch=FetchType.LAZY) //This annotation links both Student and passport as a 1to1 since only one student can have one passport
    private Passport passport; //will set this as a foreign key (primary key from another table) 
    //Setting fetch to LAZY means it will not get the passport along with Student info for performance (incase you dont want it pulled everytime)

    @ManyToMany
    @JoinTable(name="STUDENT_COURSE", 
               joinColumns= @JoinColumn(name="student_id"), 
               inverseJoinColumns= @JoinColumn(name="course_id") ) //custom join table for M2M table with custom column names
    //joinColumn - student_id
    //Inverse-joinColumn - course_id
    private List<Course> courses = new ArrayList<>();

    //Default Constructor -> Needed by JPA
    public Student(){}

    //Constructor
    public Student(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student [createdDate=" + createdDate + ", id=" + id + ", lastUpdatedDate=" + lastUpdatedDate + ", name="
                + name + "]";
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void deleteCourse(Course course){
        this.courses.remove(course);
    }



}