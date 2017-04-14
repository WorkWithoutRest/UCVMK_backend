package models;

public class course_client {
    private int client_id;
    private int course_id;

    public course_client(int client_id, int course_id) {
        this.client_id = client_id;
        this.course_id = course_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}
