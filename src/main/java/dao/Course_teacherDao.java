package dao;

import models.Course_teacher;

import java.util.List;

public interface Course_teacherDao {
    List<Integer> findTeacher(int id);
    List<Integer> findCourse(int id);
    int save(Course_teacher course_teacher);
    void update(Course_teacher course_teacher);
    void delete(int course_id, int teacher_id);
    List<Course_teacher> findAll();
}
