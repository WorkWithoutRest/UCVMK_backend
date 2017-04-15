package models;

import java.util.Date;

public class Client{
    private int id;
    private String login;
    private String name;
    private String surname;
    private String patronymic_name;
    private Date birth;
    private String email;
    private String city;
    private String street;
    private String house;
    private String appartment;
    private long telephone;
    private String status;


    public Client(int id, String login, String name, String surname, String patronymic_name, Date birth, String email, String city, String street, String house, String appartment, long telephone, String status) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.patronymic_name = patronymic_name;
        this.birth = birth;
        this.email = email;
        this.city = city;
        this.street = street;
        this.house = house;
        this.appartment = appartment;
        this.telephone = telephone;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic_name() {
        return patronymic_name;
    }

    public void setPatronymic_name(String patronymic_name) {
        this.patronymic_name = patronymic_name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getAppartment() {
        return appartment;
    }

    public void setAppartment(String appartment) {
        this.appartment = appartment;
    }

    public long getTelephone() {
        return telephone;
    }

    public void setTelephone(long telephone) {
        this.telephone = telephone;
    }

}