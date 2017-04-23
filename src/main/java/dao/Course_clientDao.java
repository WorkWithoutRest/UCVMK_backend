package dao;

import models.Client;
import models.Course;
import models.Course_client;

import java.util.List;

public interface Course_clientDao {
    List<Integer> findClient(int id);
    List<Integer> findCourse(int id);
    int save(Course_client course_client);
    void delete(int course_id, int client_id);
    List<Course_client> findAll();
}
