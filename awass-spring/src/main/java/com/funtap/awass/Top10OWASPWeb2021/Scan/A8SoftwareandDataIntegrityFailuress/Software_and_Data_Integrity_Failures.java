package com.funtap.awass.Top10OWASPWeb2021.Scan.A8SoftwareandDataIntegrityFailuress;

import com.funtap.awass.Entity.UrlOb;
import com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A8SoftwareandDataIntegrityFailures.Software_and_Data_Integrity_Failures_sig;
import com.funtap.awass.Top10OWASPWeb2021.Scan.A2CryptographicFailures.Base64EncodeSecret;
import okhttp3.*;

import java.io.IOException;
import java.util.Base64;

public class Software_and_Data_Integrity_Failures {
    public boolean Software_and_Data_Integrity_Failures(String cookie, UrlOb ob){
        Base64EncodeSecret b64 = new Base64EncodeSecret();
        String cleartext;
        if(b64.Check_Base64(cookie)){
            byte[] decodedBytes = Base64.getDecoder().decode(cookie);
            cleartext = new String(decodedBytes);
        }else{
            Software_and_Data_Integrity_Failures_sig sig = new Software_and_Data_Integrity_Failures_sig();
            String[] ListSig = sig.getSIG();
            for(String x : ListSig){
                if(cookie.contains(x)){
                    cleartext = cookie;
                }
            }
        }

        return false;
    }
    private boolean checkStatuscode( UrlOb ob,String cookies) throws IOException {
        String method = ob.getMethod();
        String param = ob.getParam();
        String url = ob.getUrl();
        if(method.equals("GET")){
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url((String) url)
                    .method("GET", null)
                    .addHeader("Cookie", cookies)
                    .build();
            Response  response = client.newCall(request).execute();
            if(response.code() == 200 || response.code() == 302){
                return true;
            }
        }
        else{
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .followRedirects(false)
                    .build();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, param);
            Request request = new Request.Builder()
                    .url(url)
                    .method("POST", body)
                    .addHeader("Cookie", cookies)
                    .build();
            Response response = client.newCall(request).execute();
            if(response.code() == 200 || response.code() == 302){
                return true;
            }
        }
        return false;
    }
}
