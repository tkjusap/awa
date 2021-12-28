package com.funtap.awass.Top10OWASPWeb2021.Scan.A1BrokenAccessControl;

import com.funtap.awass.Entity.UrlOb;
import com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A1BrokenAccessControl.LFI;
import okhttp3.*;

import java.io.IOException;

public class Directory_Traversal_Files {
    public boolean Get_request_URL(UrlOb ob,String cookies) throws IOException {
        String method = ob.getMethod();
        String param = ob.getParam();
        String url = ob.getUrl();
        LFI lfi = new LFI();
        String[] payload = lfi.getArrPayLFI();
        String[] Sig = lfi.getArrSigLFI();

        for (String x : payload) {
            int code = getStatuscode(ob,cookies,x);
            System.out.println(code);
            for (String signature : Sig) {
                if (code == 200) {

                    return true;
                } else {
                }
            }
        }
        return false;
    }

    private int getStatuscode(UrlOb ob, String cookies,String x) throws IOException {
        String method = ob.getMethod();
        String param = ob.getParam();
        String url = ob.getUrl();
        if (cookies != null) {
            if (method.equals("GET")) {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                Request request = new Request.Builder()
                        .url((String) url+x)
                        .method("GET", null)
                        .addHeader("Host", "localhost")
                        .addHeader("Cookie", cookies)
                        .build();
                Response response = client.newCall(request).execute();
                return response.code();
            } else {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .followRedirects(false)
                        .build();
                MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                RequestBody body = RequestBody.create(mediaType, param+x);
                Request request = new Request.Builder()
                        .url(url)
                        .method("POST", body)
                        .addHeader("Host", "localhost")
                        .addHeader("Cookie", cookies)
                        .build();
                Response response = client.newCall(request).execute();
                return response.code();
            }

        }
        else{
            if (method.equals("GET")) {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                Request request = new Request.Builder()
                        .url((String) url+x)
                        .method("GET", null)
                        .addHeader("Host", "localhost")
                        .build();
                Response response = client.newCall(request).execute();
                return response.code();
            } else {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .followRedirects(false)
                        .build();
                MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                RequestBody body = RequestBody.create(mediaType, param+x);
                Request request = new Request.Builder()
                        .url(url)
                        .method("POST", body)
                        .addHeader("Host", "localhost")                        .build();
                Response response = client.newCall(request).execute();

                return response.code();
            }
        }
    }
}
