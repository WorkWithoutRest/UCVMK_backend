package services;

import dao.TeacherDaoImpl;
import models.Teacher;

import java.util.List;

public class TeacherService {
    public static List<Teacher> get_Teacher() {
        TeacherDaoImpl teacherDao = new TeacherDaoImpl();
        return teacherDao.findAll();
    }
}
