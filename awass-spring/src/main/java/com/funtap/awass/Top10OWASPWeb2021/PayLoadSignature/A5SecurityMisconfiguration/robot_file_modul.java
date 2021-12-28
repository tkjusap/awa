package com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A5SecurityMisconfiguration;

import java.util.ArrayList;
import java.util.List;

public class robot_file_modul {
    private String User_agent = "";
    private List<String> Allow =  new ArrayList<String>();
    private List<String> Disallow =  new ArrayList<String>();

    public robot_file_modul(String user_agent, List<String> allow, List<String> disallow) {
        User_agent = user_agent;
        Allow = allow;
        Disallow = disallow;
    }

    public robot_file_modul() {
    }

    public String getUser_agent() {
        return User_agent;
    }

    public void setUser_agent(String user_agent) {
        User_agent = user_agent;
    }

    public List<String> getAllow() {
        return Allow;
    }

    public void setAllow(List<String> allow) {
        Allow = allow;
    }

    public List<String> getDisallow() {
        return Disallow;
    }

    public void setDisallow(List<String> disallow) {
        Disallow = disallow;
    }

    @Override
    public String toString() {
        return "robot_file_modul{" +
                "User_agent='" + User_agent + '\'' +
                ", Allow=" + Allow +
                ", Disallow=" + Disallow +
                '}';
    }
}