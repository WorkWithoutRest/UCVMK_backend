package models;

public class teacher {
    private int id;
    private String name;
    private String link;
    private String login;

    public teacher(int id, String name, String link, String login) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
