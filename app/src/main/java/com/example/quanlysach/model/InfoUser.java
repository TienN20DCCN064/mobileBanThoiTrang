package com.example.quanlysach.model;

public class InfoUser {
    int id;
    String fullname, numbPhone, email;
    int account_id;

    public InfoUser() {
    }

    public InfoUser(int id, String fullname, String numbPhone, String email, int account_id) {
        this.id = id;
        this.fullname = fullname;
        this.numbPhone = numbPhone;
        this.email = email;
        this.account_id = account_id;
    }

    public InfoUser(String fullname, String numbPhone, String email, int account_id) {
        this.fullname = fullname;
        this.numbPhone = numbPhone;
        this.email = email;
        this.account_id = account_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNumbPhone() {
        return numbPhone;
    }

    public void setNumbPhone(String numbPhone) {
        this.numbPhone = numbPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    @Override
    public String toString() {
        return "InfoUser{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", numbPhone='" + numbPhone + '\'' +
                ", email='" + email + '\'' +
                ", account_id=" + account_id +
                '}';
    }
}
