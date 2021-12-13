package com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A4InsecureDesign;

public class sensitive_file_payload {
    private String[] signature;
    public sensitive_file_payload(){
        signature = new  String[]{
          ".sql",
          "passwd.txt",
          "etc",
          ".log"
        };
    }
}
