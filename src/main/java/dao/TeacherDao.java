package dao;

import models.Teacher;

import java.util.List;

public interface TeacherDao {
    Teacher find(int id);
    int save(Teacher teacher);
    void update(Teacher teacher);
    void delete(int id);
    List<Teacher> findAll();
}
