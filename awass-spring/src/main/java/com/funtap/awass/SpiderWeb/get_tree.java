package com.funtap.awass.SpiderWeb;

import com.funtap.awass.Entity.UrlOb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class get_tree {
    public void get_tree(List<UrlOb> ListOb){
        List<String>  ListAllUrl = new ArrayList<>();
        ListAllUrl = getUrl(ListOb);
        HashMap<String, Integer> MapUrlTree = new Map<String, Integer>();
    }
    private List<String> getUrl(List<UrlOb> ListOb){
        List<String>  ListAllUrl = new ArrayList<>();
        for (UrlOb x : ListOb){
            ListAllUrl.add(x.getUrl());
        }
        return ListAllUrl;
    }
}
