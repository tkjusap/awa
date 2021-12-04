package com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A7IdentificationandAuthenticationFailures;

public class administrative_Portals_payload {
    private String[] administrative_Portals;
    public administrative_Portals_payload(){
        administrative_Portals = new String[]{"admin=", "nonadim", "noadmon"};
    }

    public String[] getAdministrative_Portals() {
        return administrative_Portals;
    }
}
