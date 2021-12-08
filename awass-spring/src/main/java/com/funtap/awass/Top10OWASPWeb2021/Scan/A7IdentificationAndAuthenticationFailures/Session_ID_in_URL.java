package com.funtap.awass.Top10OWASPWeb2021.Scan.A7IdentificationAndAuthenticationFailures;

import com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A7IdentificationandAuthenticationFailures.Session_ID_in_URL_payload;

public class Session_ID_in_URL {

    public Boolean CheckSessionIdInUrl(String url){
        Session_ID_in_URL_payload sid = new Session_ID_in_URL_payload();
        String[] ListSidPayload = sid.getSessionPayload();
        for(String x : ListSidPayload){
            if(url.contains(x))
                //
                return true;

        }
        //System.out.println("this site is safe");
        return false;
    }
}
