package com.funtap.awass.Top10OWASPWeb2021.Scan.A6VulnerableandOutdatedComponents;

import com.funtap.awass.Entity.UrlOb;
import okhttp3.*;

import java.io.IOException;
import java.util.Locale;

public class PHP_CGI_RCE {
    public boolean PHP_CGI_RCE(String cookie, UrlOb ob ) throws IOException {

        String html = GetHtmlString(ob,cookie);
        if(html.toLowerCase(Locale.ROOT).contains("<?php"))
            return true;
        else
        return false;
    }



    private String GetHtmlString(UrlOb ob, String cookies) throws IOException {
        String method = ob.getMethod();
        String param = ob.getParam();
        String url = ob.getUrl();
        Response response=null;
        if(url.endsWith("/"))
            url = url+"?-s";
        else
            url = url+"/?-s";
        String html = "";
        if (cookies != null) {
            if (method.equals("GET")) {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                Request request = new Request.Builder()
                        .url((String) url)
                        .method("GET", null)
                        .addHeader("Cookie", cookies)
                        .build();
                 response = client.newCall(request).execute();
            } else {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .followRedirects(false)
                        .build();
                MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                RequestBody body = RequestBody.create(mediaType, param );
                Request request = new Request.Builder()
                        .url(url)
                        .method("POST", body)
                        .addHeader("Cookie", cookies)
                        .build();
                 response = client.newCall(request).execute();
            }

        } else {
            if (method.equals("GET")) {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                Request request = new Request.Builder()
                        .url((String) url)
                        .method("GET", null)
                        .build();
                 response = client.newCall(request).execute();
            } else {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .followRedirects(false)
                        .build();
                MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                RequestBody body = RequestBody.create(mediaType, param);
                Request request = new Request.Builder()
                        .url(url)
                        .method("POST", body)
                        .build();
                 response = client.newCall(request).execute();
            }
        }
            html = response.body().string();
        return html;
    }
}
