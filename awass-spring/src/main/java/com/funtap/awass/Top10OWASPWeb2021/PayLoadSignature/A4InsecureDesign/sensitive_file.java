package com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A4InsecureDesign;

public class sensitive_file {
    private String[] senFile;
    public sensitive_file(){
        senFile = new String[]{
                "sql",
                "zip",
                "log",
                "mail",
        };
    }

    public String[] getSenFile() {
        return senFile;
    }
}
