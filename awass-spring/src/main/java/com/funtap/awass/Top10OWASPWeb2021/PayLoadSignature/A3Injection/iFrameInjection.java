package com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A3Injection;

public class iFrameInjection {
    private static String[] arrPayii;

    public iFrameInjection() {
        arrPayii = new String[]{
                "%3Ciframe%3E%3Ch1%3ETest12345%3C%2Fh1%3E%3C%2Fiframe%3E",};
    }
    public String[] getArrPayXSS() {
        return arrPayii;
    }
}
