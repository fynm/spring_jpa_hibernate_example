package com.example.javaPractice.jpaHibernate.basics.springJpaHibernateExample;

import com.example.javaPractice.jpaHibernate.entity.Course;
import com.example.javaPractice.jpaHibernate.repository.CourseRepo;

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
https://stackoverflow.com/questions/29221645/cant-autowire-repository-annotated-interface-in-spring-boot
*/

@SpringBootApplication
@EnableJpaRepositories("com.example.javaPractice.jpaHibernate.repository")
@EntityScan("com.example.javaPractice.jpaHibernate.entity")
@ComponentScan("com.example.javaPractice.jpaHibernate.repository")
public class SpringJpaHibernateExampleApplication implements CommandLineRunner{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepo repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaHibernateExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Course course = repository.findById(10001L);
		logger.info("Course 10001 -> {}", course.toString());
		//repository.deleteById(10001L);
		repository.save(new Course("Wombology 101"));
		repository.playWithEnitityManager();
	}

}
