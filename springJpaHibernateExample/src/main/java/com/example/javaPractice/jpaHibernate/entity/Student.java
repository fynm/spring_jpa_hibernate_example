package com.example.javaPractice.jpaHibernate.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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



}