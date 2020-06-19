insert into course(id, fullname, created_date, last_updated_date) values(10001, 'JPA in 50 steps', sysdate(), sysdate());
insert into course(id, fullname, created_date, last_updated_date) values(10002, 'JPA in 500 steps', sysdate(), sysdate());
insert into course(id, fullname, created_date, last_updated_date) values(10003, 'JPA in 5 steps', sysdate(), sysdate());


insert into review(id, description, rating, created_date, last_updated_date, course_id) values (40001, 'Its wow',5, sysdate(), sysdate(), 10001);
insert into review(id, description, rating, created_date, last_updated_date, course_id) values (40002, 'Its good',4, sysdate(), sysdate(), 10001);
insert into review(id, description, rating, created_date, last_updated_date, course_id) values (40003, 'Its ok',3, sysdate(), sysdate(), 10001);
insert into review(id, description, rating, created_date, last_updated_date, course_id) values (40004, 'Sheeeeeit',1, sysdate(), sysdate(), 10002);

insert into passport(id, number, created_date, last_updated_date) values (30001, 'E1421365', sysdate(), sysdate());
insert into passport(id, number, created_date, last_updated_date) values (30002, 'E6347145', sysdate(), sysdate());
insert into passport(id, number, created_date, last_updated_date) values (30003, 'E3562346', sysdate(), sysdate());
insert into passport(id, number, created_date, last_updated_date) values (30004, 'E1463724', sysdate(), sysdate());

insert into student(id, name, created_date, last_updated_date, passport_id) values (20001, 'Shawn Fong', sysdate(), sysdate(), 30001);
insert into student(id, name, created_date, last_updated_date, passport_id) values (20002, 'Monica Yan', sysdate(), sysdate(), 30002);
insert into student(id, name, created_date, last_updated_date, passport_id) values (20003, 'Poland Spring', sysdate(), sysdate(), 30003);
insert into student(id, name, created_date, last_updated_date, passport_id) values (20004, 'Patrick Star', sysdate(), sysdate(), 30004);

insert into student_course(student_id, course_id) values (20001, 10001);
insert into student_course(student_id, course_id) values (20001, 10002);
insert into student_course(student_id, course_id) values (20003, 10001);
insert into student_course(student_id, course_id) values (20002, 10001);
insert into student_course(student_id, course_id) values (20002, 10002);
insert into student_course(student_id, course_id) values (20004, 10002);




