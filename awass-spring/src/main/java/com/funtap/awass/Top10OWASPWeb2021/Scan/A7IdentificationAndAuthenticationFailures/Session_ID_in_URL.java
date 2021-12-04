package com.funtap.awass.Top10OWASPWeb2021.Scan.A7IdentificationAndAuthenticationFailures;

import com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A7IdentificationandAuthenticationFailures.Session_ID_in_URL_payload;

public class Session_ID_in_URL {
    private String url;
    public Session_ID_in_URL(){}

    public void setUrl(String url) {
        this.url = url;
    }
    public Boolean CheckSessionIdInUrl(){
        Session_ID_in_URL_payload sid = new Session_ID_in_URL_payload();
        String[] ListSidPayload = sid.getSessionPayload();
        for(String x : ListSidPayload){
            if(url.contains(x))
                //System.out.println("session id in url");
                return true;

        }
        //System.out.println("this site is safe");
        return false;
    }
}
