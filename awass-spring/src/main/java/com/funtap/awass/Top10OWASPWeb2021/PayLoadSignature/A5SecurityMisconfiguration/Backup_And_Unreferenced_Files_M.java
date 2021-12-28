package com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A5SecurityMisconfiguration;

public class Backup_And_Unreferenced_Files_M {
    private String domain="";
    private String payloa="";
    public Backup_And_Unreferenced_Files_M() {
    }
    public Backup_And_Unreferenced_Files_M(String domain) {
        this.domain = domain;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }
    public String getPayloa() {
        payloa = "https://www.google.com.vn/search?q=site%3A%22"+domain+"%22+intitle%3A%22index.of%22+intext%3A%28backup%7Cbank%7Cdump%29";
        //payloa = "https://www.google.com.vn/search?q=site:%22elearning.olade.org%22+intitle:%22index.of%22+intext:(backup%7Cbank%7Cdump)&gbv=2&sei=Cay5YYznH8Tx-Qbgl56wBQ";
        return payloa;
    }
}
