package com.funtap.awass.Entity;

import java.util.ArrayList;
import java.util.List;

public class tree {
    List<String> url = new ArrayList<>();
    int index;

    public tree() {

    }

    public tree(List<String> url, int index) {
        this.url = url;
        this.index = index;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "tree{" +
                "url=" + url +
                ", index=" + index +
                '}';
    }
}
