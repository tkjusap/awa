package com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A3Injection;

public class HTMLInjection {
    private static String[] arrPayhtmli;

    public HTMLInjection() {
        arrPayhtmli = new String[]{
                "%3Ch1%3ETest12345%3C%2Fh1%3E",
        };
    }
    public String[] getArrPayhtmli() {
        return arrPayhtmli;
    }
}
