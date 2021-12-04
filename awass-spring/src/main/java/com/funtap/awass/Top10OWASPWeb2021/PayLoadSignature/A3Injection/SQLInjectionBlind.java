package com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A3Injection;

public class SQLInjectionBlind {
    private static String[] arrPayBlindSQLin;

    public SQLInjectionBlind() {
        /*List Payload SQL Injection boolen*/
        arrPayBlindSQLin = new String[]{
                "1 and 1=1#",
                "1 and 1=2#",
                "1' and 1=1#",
                "1' and 1=2#",
                "1\" and 1=1#",
                "1\" and 1=2#"
        };
    }
    public String[] getArrPaySQLin() {
        return arrPayBlindSQLin;
    }
}
