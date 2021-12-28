package com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A2CryptographicFailures;

public class Text_File {
    private String[] intext;

    public Text_File() {
        intext = new String[]{
                "account",
                "passwd",
                "pass",
                "user"

        };
    }

    public String[] getIntext() {
        return intext;
    }
}
