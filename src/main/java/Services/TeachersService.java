package services;

import dao.TeacherDaoImpl;
import models.Teacher;

import java.util.List;

/**
 * Created by Dmitrij on 15.05.2017.
 */
public class TeachersService {

    public static List<Teacher> get_Teacher() {
        TeacherDaoImpl teacherDao = new TeacherDaoImpl();
        return teacherDao.findAll();
    }

}
