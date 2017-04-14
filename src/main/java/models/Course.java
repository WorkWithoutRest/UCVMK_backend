package models;

import javafx.scene.text.Text;

import java.util.Date;

public class Course {
    private int id;
    private String title;
    private Text text;
    private Date date_start;
    private Date date_finish;
    private Boolean status;

    public Course(int id, String title, Text text, Date date_start, Date date_finish, Boolean status) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date_start = date_start;
        this.date_finish = date_finish;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_finish() {
        return date_finish;
    }

    public void setDate_finish(Date date_finish) {
        this.date_finish = date_finish;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
