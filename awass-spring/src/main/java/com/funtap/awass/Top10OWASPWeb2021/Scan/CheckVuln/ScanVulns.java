package com.funtap.awass.Top10OWASPWeb2021.Scan.CheckVuln;

import com.funtap.awass.Entity.UrlOb;
import com.funtap.awass.Top10OWASPWeb2021.Scan.A1BrokenAccessControl.Directory_Traversal_Files;
import com.funtap.awass.Top10OWASPWeb2021.Scan.A2CryptographicFailures.Base64EncodeSecret;
import com.funtap.awass.Top10OWASPWeb2021.Scan.A2CryptographicFailures.Host_Header_Attack;
import com.funtap.awass.Top10OWASPWeb2021.Scan.A7IdentificationAndAuthenticationFailures.Administrative_Portals;
import com.funtap.awass.Top10OWASPWeb2021.Scan.A7IdentificationAndAuthenticationFailures.Insecure_Login_Forms;
import com.funtap.awass.Top10OWASPWeb2021.Scan.A7IdentificationAndAuthenticationFailures.Session_ID_in_URL;
import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScanVulns {
    public void ScanVulns(String[] payload, String[] signature, UrlOb urlobject) throws IOException {
        if (urlobject.getParam() != null && urlobject.getMethod().equalsIgnoreCase("POST")) {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .followRedirects(false)
                    .build();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, urlobject.getParam());
            Request request = new Request.Builder()
                    .url(urlobject.getUrl())
                    .method("POST", body)
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println("dang scan url " + urlobject.getUrl() + ", method " + urlobject.getMethod() + ", param: " + urlobject.getParam());
        } else if (urlobject.getParam() != null && urlobject.getMethod().equalsIgnoreCase("GET")) {

        }
    }

    public void ScanVulcheck(UrlOb urlobject, String cookie) throws IOException {
        Directory_Traversal_Files DTF = new Directory_Traversal_Files();
        Base64EncodeSecret base64Encode = new Base64EncodeSecret();
        Host_Header_Attack header_attack = new Host_Header_Attack();
        Administrative_Portals admPortal = new Administrative_Portals();
        Insecure_Login_Forms insLoginForm = new Insecure_Login_Forms();
        Session_ID_in_URL session_Id_in_url = new Session_ID_in_URL();
        if (urlobject.getParam() != null && !checkFile(urlobject.getParam())) {
            //Directory Traversal Files
            // if(DTF.Get_request_URL(urlobject.getUrl())){
            //    System.out.println("this site have vul Traversal_Files");
            // }
            // Base 64 Encode Secret
            if (cookie != null) {
                if (base64Encode.Check_Base64(cookie)) {
                    System.out.println("cookie be encoding by base64 vuln");
                }
            }
            //Host_Header_Attack
            if (header_attack.GetHeaders(urlobject.getUrl())) {
                System.out.println("this site have vul Host Header Attack");
            }
            //Administrative Portals
            if (urlobject.getParam() != null) {
                if (admPortal.Check_Administrative_Portals(urlobject.getParam())) {
                    System.out.println("this site be Administrative_Portals vuln");
                }
            }
            //Insecure Login Forms
            if (insLoginForm.Insecure_Login_Forms(urlobject.getUrl(), cookie)) {
                System.out.println("this site Insecure Login Forms");
            }
            //
            if (urlobject.getParam() != null) {
                if (session_Id_in_url.CheckSessionIdInUrl(urlobject.getParam())) {
                    System.out.println("session id in url vuln");
                }
            }
        }
    }

    private boolean checkFile(String param) {
        if (param.toLowerCase(Locale.ROOT).contains(".jpg")
                || param.toLowerCase(Locale.ROOT).contains(".png")
                || param.toLowerCase(Locale.ROOT).contains(".js")
                || param.toLowerCase(Locale.ROOT).contains(".doc")
                || param.toLowerCase(Locale.ROOT).contains(".xml")
                || param.toLowerCase(Locale.ROOT).contains(".gif")
                || param.toLowerCase(Locale.ROOT).contains(".json")
                || param.toLowerCase(Locale.ROOT).contains(".css")
        ) {
            return true;
        } else {
            return false;
        }
    }
}

