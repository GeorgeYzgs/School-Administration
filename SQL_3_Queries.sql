USE SCHOOL_PROJECT;

SELECT * FROM STUDENTS;

SELECT * FROM TRAINERS;

SELECT * FROM ASSIGNMENTS;

SELECT * FROM COURSES;

SELECT * FROM students s JOIN students_per_Course  USING (student_id) JOIN courses c USING (course_id);

SELECT * FROM trainers t JOIN trainers_per_course USING (trainer_id) JOIN courses c USING (course_id);

SELECT * FROM assignments a JOIN courses USING (course_id);

SELECT s.student_id,first_name,last_name,a.title,a.description,apsc.total_mark,apsc.sub_date,c.title,c.stream,c.type
FROM ASSIGNMENTS_PER_STUDENT_PER_COURSE apsc INNER JOIN STUDENTS s
ON s.STUDENT_ID= apsc.STUDENT_ID
INNER JOIN ASSIGNMENTS a ON apsc.ASSIGNMENT_ID = A.ASSIGNMENT_ID
INNER JOIN COURSES c ON a.COURSE_ID = C.COURSE_ID;

select * from students  JOIN students_per_Course  USING (student_id) JOIN courses c USING (course_id) where student_id in 
(SELECT student_id FROM Students_per_course GROUP BY student_id HAVING COUNT(*) > 1)
ORDER BY STUDENT_ID;

---------------------------- SIMILAR QUERIES, USED IN JAVA TO ASSOCIATE ENTITIES --------------------------------------------

SELECT c.course_id,t.trainer_id FROM trainers t JOIN trainers_per_course USING (trainer_id) JOIN courses c USING (course_id);

SELECT c.course_id,s.student_id FROM students s JOIN students_per_Course  USING (student_id) JOIN courses c USING (course_id);

SELECT s.STUDENT_ID, a.ASSIGNMENT_ID,a.TITLE,a.DESCRIPTION,a.ORAL_MARK,apsc.TOTAL_MARK,apsc.SUB_DATE 
FROM ASSIGNMENTS_PER_STUDENT_PER_COURSE apsc INNER JOIN STUDENTS s
ON s.STUDENT_ID= apsc.STUDENT_ID
INNER JOIN ASSIGNMENTS a ON apsc.ASSIGNMENT_ID = a.ASSIGNMENT_ID;