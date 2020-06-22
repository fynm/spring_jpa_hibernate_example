package com.example.javaPractice.jpaHibernate.repository;

import java.util.List;

import com.example.javaPractice.jpaHibernate.entity.Course;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "courses") // sets this as a REST Resource and is exposed to Rest Services
//@JsonIgnoreType
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

    /*Custom Queries -mimics avoid in JPA but can change findById to findBy*columnName* -> same with countBy, deleteBy etc */


    List<Course> findByName(String name);
    List<Course> findByNameAndId(String name, Long id);
    List<Course> countByName(String name);
    List<Course> findByNameOrderByIdDesc(String name);
    List<Course> deleteByName(String name);
    
    /*Custom Specific Queries */
    @Query("Select c From Course c where name like 'Wombology%'")
    List<Course> courseWithWombologyInName();

    @Query(value="select * from Course c where name like 'Wombology%'", nativeQuery=true)
    List<Course> courseWithWombologyInNameNative();

    @Query(name="query_get_all_courses_wombo")
    List<Course> courseWithWombologyInNameNamedQuery();


}