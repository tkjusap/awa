package com.funtap.awass.Entity;

public class Account {
    private String username;
    private String password;
    private String email;
    private String fullname;
    private String mnv;
    private String team;

    public Account() {
    }

    public Account(String username, String password, String email, String fullname, String mnv, String team) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.mnv = mnv;
        this.team = team;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMnv() {
        return mnv;
    }

    public void setMnv(String mnv) {
        this.mnv = mnv;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", fullname='" + fullname + '\'' +
                ", mnv='" + mnv + '\'' +
                ", team='" + team + '\'' +
                '}';
    }
}
