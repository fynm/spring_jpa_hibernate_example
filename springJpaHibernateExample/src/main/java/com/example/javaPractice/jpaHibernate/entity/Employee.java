package com.example.javaPractice.jpaHibernate.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//@Entity
//@Inheritance(strategy=InheritanceType) //default strat -> InheritanceType.SINGLE_TABLE , 
                                              //Not default -> InheritanceType.TABLE_PER_CLASS -> Table for full / part
                                              // not default -> InheritanceType.JOINED -> table for employee / full / part
@MappedSuperclass                             //if a mapped superclass it takes over for inheritance and cannot be an entity
//MappedSuperClass removes inheritance functionality and will just make it so that PT and FT just share some columns but are separate tables
/*Which version of inheritance to use?

Single_Table -> Best for Performance -> everything will be stored in a table

Table_Per_Class -> Not the best option -> Repeated tables and not as good performance

Joined -> Data Integrity / Data Quality -> Each column is only posted once and are linked by a foreign key

MappedSuperClass -> Not really a point with inheritance, this exists but only for super narrow usecases

*/
@DiscriminatorColumn(name="EmployeeType") //used to rename the Data Type column generated with an inherit table
public abstract class Employee {
    @Id // Sets it as a Primary Key
    @GeneratedValue // Will create an increasing Id for each id made
    private Long id;

    @Column
    private String name;

    //Default Constructor -> Needed by JPA
    protected Employee(){}

    //Constructor
    public Employee(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}