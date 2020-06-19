package com.example.javaPractice.jpaHibernate.basics.springJpaHibernateExample;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.javaPractice.jpaHibernate.entity.Course;
import com.example.javaPractice.jpaHibernate.entity.FullTimeEmployee;
import com.example.javaPractice.jpaHibernate.entity.PartTimeEmployee;
import com.example.javaPractice.jpaHibernate.entity.Review;
import com.example.javaPractice.jpaHibernate.entity.Student;
import com.example.javaPractice.jpaHibernate.repository.StudentRepo;
import com.example.javaPractice.jpaHibernate.repository.CourseRepo;
import com.example.javaPractice.jpaHibernate.repository.EmployeeRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/*Could not initially find repo due to different package folder -> EntityScan/Component Scan fixed the issue 
https://stackoverflow.com/questions/29221645/cant-autowire-courseRepository-annotated-interface-in-spring-boot
*/

@SpringBootApplication
@EnableJpaRepositories("com.example.javaPractice.jpaHibernate.repository")
@EntityScan("com.example.javaPractice.jpaHibernate.entity")
@ComponentScan("com.example.javaPractice.jpaHibernate.repository")
public class SpringJpaHibernateExampleApplication implements CommandLineRunner{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepo courseRepository;

	@Autowired
	private StudentRepo studentRepository;
	
	@Autowired
	private EmployeeRepo EmployeeRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaHibernateExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Course course = courseRepository.findById(10001L);
		logger.info("Course 10001 -> {}", course.toString());
		//courseRepository.deleteById(10001L);
		courseRepository.save(new Course("Wombology 101"));
		courseRepository.playWithEnitityManager();
		studentRepository.saveStudentWithPassport();
		courseRepository.addReviewsForCourse();
		List<Review> listOfReviews = new ArrayList<>();
		Review review1 = new Review("Its gawd damn amazing", 5);
		Review review2 = new Review("Its sheit", 1);
		listOfReviews.add(review1);
		listOfReviews.add(review2);
		courseRepository.addReview(10002L, listOfReviews);
		studentRepository.insertStudentAndCourse();
		Student student = new Student("James");
		Course course1 = new Course("Wow the best course");
		studentRepository.insertStudentAndCourse(student, course1);

		//------------------------------------------------------------

		EmployeeRepository.insert(new PartTimeEmployee("Jack", new BigDecimal("50")));
		EmployeeRepository.insert(new FullTimeEmployee("Coco", new BigDecimal("10000")));

		//logger.info("All Employees -> {}", EmployeeRepository.retrieveAllEmployees());
		logger.info("All PT Employees -> {}", EmployeeRepository.retrieveAllPartTimeEmployees());
		logger.info("All FT Employees -> {}", EmployeeRepository.retrieveAllFullTimeEmployees());
	}

}
