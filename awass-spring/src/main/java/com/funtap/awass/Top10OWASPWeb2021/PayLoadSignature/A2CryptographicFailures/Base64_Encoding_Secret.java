package com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A2CryptographicFailures;

public class Base64_Encoding_Secret {
    private String SIGBase64 ;
    public Base64_Encoding_Secret(){
        SIGBase64 = "^(?:[A-Za-z0-9+\\/]{4})*(?:[A-Za-z0-9+\\/]{2}==|[A-Za-z0-9+\\/]{3}=)?$";

    }
    public String getSIGBase64(){
        return SIGBase64;
    }
}
 
