package com.funtap.awass.Entity;

import java.util.ArrayList;
import java.util.List;

public class tree {
  String father;
  String chill;

    public tree() {
    }

    public tree(String father, String chill) {
        this.father = father;
        this.chill = chill;
    }

    @Override
    public String toString() {
        return "tree{" +
                "father='" + father + '\'' +
                ", chill='" + chill + '\'' +
                '}';
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getChill() {
        return chill;
    }

    public void setChill(String chill) {
        this.chill = chill;
    }
}
