package services;

import dao.CourseDaoImpl;
import dao.Course_clientDaoImpl;
import models.Course;
import models.Course_client;

import java.util.List;

/**
 * Created by Dmitrij on 15.05.2017.
 */
public class CourseService {

    public static void registration_on_course( int client_id,int course_id) {
        Course_client course_client = new Course_client(client_id,course_id);
        Course_clientDaoImpl course_clientDao = new Course_clientDaoImpl();
        if (course_clientDao.check_course_for_client(client_id,course_id)) {
            //ERROR
        }
        else {
            course_clientDao.save(course_client);
        }
    }

    public static List<Course> getCourses() {
        CourseDaoImpl courseDao = new CourseDaoImpl();
        return courseDao.findAll();
    }
}
