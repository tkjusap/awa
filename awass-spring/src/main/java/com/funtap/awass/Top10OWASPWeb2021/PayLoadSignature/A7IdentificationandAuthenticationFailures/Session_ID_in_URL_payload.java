package com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A7IdentificationandAuthenticationFailures;


public class Session_ID_in_URL_payload {
    private String[] SessionPayload;
    public Session_ID_in_URL_payload(){
        SessionPayload=new String[]{
                "sessionID",
                "Sid",
                "token",
                "cookie",
                "pid"
        };
    }
    public String[] getSessionPayload() {
        return SessionPayload;
    }
}