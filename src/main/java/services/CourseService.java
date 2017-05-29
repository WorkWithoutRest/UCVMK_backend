package services;

import dao.CourseDaoImpl;
import dao.Course_clientDaoImpl;
import models.Course;
import models.Course_client;

import java.util.List;

public class CourseService {

        public static void registration_on_course( int client_id,int course_id) {
            Course_client course_client = new Course_client(client_id,course_id);
            Course_clientDaoImpl course_clientDao = new Course_clientDaoImpl();
            if (course_clientDao.checkCourseForClient(client_id,course_id)) {
                course_clientDao.save(course_client);
            }
            else {
                //ERROR
            }
        }

        public static List<Course> getCourses() {
            CourseDaoImpl courseDao = new CourseDaoImpl();
            return courseDao.findAll();
        }
    }

