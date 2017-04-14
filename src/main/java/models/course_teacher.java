package models;

public class course_teacher {
    private int course_id;
    private int teacher_id;

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public course_teacher(int course_id, int teacher_id) {

        this.course_id = course_id;
        this.teacher_id = teacher_id;
    }
}
