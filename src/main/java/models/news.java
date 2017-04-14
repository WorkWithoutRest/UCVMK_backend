package models;

import javafx.scene.text.Text;

import java.util.Date;

public class news {
    private int id;
    private Date date;
    private Text text;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public news(int id, Date date, Text text) {

        this.id = id;
        this.date = date;
        this.text = text;
    }
}
