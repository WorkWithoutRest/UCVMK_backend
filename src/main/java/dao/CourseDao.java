package dao;

import models.Course;

import java.util.List;

public interface CourseDao {
    Course find(int id);
    int save(Course course);
    void update(Course course);
    void delete(int id);
    List<Course> findAll();
}
