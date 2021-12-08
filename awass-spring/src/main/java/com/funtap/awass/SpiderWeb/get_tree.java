package com.funtap.awass.SpiderWeb;

import com.funtap.awass.Entity.UrlOb;
import com.funtap.awass.Entity.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class get_tree {
    public void get_tree(String[] ListUrl, String domain) {
        List<tree> ListTree = new ArrayList<>();
        for (String x : ListUrl) {
            if (!x.equals(domain)) {
                if (x.endsWith("/"))
                    x = x.substring(0, x.length() - 1);
                String father = "";
                String child = "";
                boolean conn = true;
                while (conn) {
                    if (x == "")
                        conn = false;
                    if (x.contains(domain)) {
                        if ((x.replace(domain, "").split("/").length == 1)) {
                            father = domain;
                            child = x.replace(domain, "");
                            x = x.replace(domain, "");
                            conn = false;
                        } else {
                            father = domain;
                            x = x.replace(domain, "");
                            child = x.split("/")[0];
                        }
                    } else {
                        if (x.split("/").length == 1) {
                            child = x;
                            conn = false;
                        } else {
                            father = x.split("/")[0];
                            x = x.replace(father + "/", "");
                            child = x.split("/")[0];
                        }

                    }
                    if (father.endsWith("/"))
                        father = father.substring(0, father.length() - 1);
                    if (child.endsWith("/"))
                        child = child.substring(0, child.length() - 1);
                    tree t = new tree(father, child);
                    ListTree.add(t);
                }
            }
        }
        //tree
        ListTree = removeDub(ListTree);
        for (tree t : ListTree) {
            System.out.println(t.getFather() + "=" + t.getChill());
        }


    }

    private List<tree> removeDub(List<tree> ListTree) {

        List<tree> NewList = new ArrayList<>();
        for (tree x : ListTree) {
            boolean contain = false;
            if (NewList.size() != 0) {
                for (tree newX : NewList) {
                    if (x.getFather().equals(newX.getFather()) && x.getChill().equals(newX.getChill())) {
                        contain = true;
                        break;
                    }
                }
            }
            if (contain == false)
                NewList.add(x);
        }
        return NewList;
    }
}
