package com.funtap.awass.Top10OWASPWeb2021.Scan.A7IdentificationAndAuthenticationFailures;
import com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A7IdentificationandAuthenticationFailures.administrative_Portals_payload;

public class Administrative_Portals {
    public boolean Check_Administrative_Portals(String url){
        administrative_Portals_payload adPayload = new administrative_Portals_payload();
        String[] payload = adPayload.getAdministrative_Portals();
        for(String x : payload){
            if(url.contains(x)){
                //System.out.println("this site be Administrative_Portals ");
                return true;
            }
        }
        //System.out.println("this site is save");
        return false;
    }
}
