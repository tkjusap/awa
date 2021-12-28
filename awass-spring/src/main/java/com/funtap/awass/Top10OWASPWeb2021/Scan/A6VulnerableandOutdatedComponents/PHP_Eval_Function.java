package com.funtap.awass.Top10OWASPWeb2021.Scan.A6VulnerableandOutdatedComponents;

import com.funtap.awass.Entity.UrlOb;
import com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A6VulnerableandOutdatedComponents.PHP_Eval;
import okhttp3.*;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class PHP_Eval_Function {
    public boolean PHP_Eval_Funtion(UrlOb urlOB, String cookie) throws IOException {
        PHP_Eval php = new PHP_Eval();
        String[] payload = php.getPayload();
        String[] signature = php.getSignature();
        if (urlOB.getParam()!=null && urlOB.getUrl().endsWith("php")) {
            for (String x : payload) {
                String html = "";
                if (urlOB.getMethod().equals("GET")) {
                    String url = urlOB.getUrl() + x;
                    html = GetHtmlString(url, cookie);
                } else if (urlOB.getMethod().equals("POST")) {
                    String param = urlOB.getParam() + x;
                    html = PostHtmlString(urlOB.getUrl(), param, cookie);
                }
                for (String sig : signature) {
                    if (html.contains(sig))
                        return true;
                }
            }
        }
//add   
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

    private String PostHtmlString(String url, String param, String cookie) throws IOException {
        String html = "";
        if (cookie == null) {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .followRedirects(false)
                    .build();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, param);
            Request request = new Request.Builder()
                    .url(url)
                    .method("POST", body)
                    .addHeader("Connection", "close")
                    .build();
            Response response = client.newCall(request).execute();
            html = response.body().string();

        } else {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .followRedirects(false)
                    .build();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, param);
            Request request = new Request.Builder()
                    .url(url)
                    .method("POST", body)
                    .addHeader("Connection", "close")
                    .addHeader("Cookie", cookie)
                    .addHeader("Cookie", cookie)
                    .build();
            Response response = client.newCall(request).execute();
            html = response.body().string();
        }
        return html;
    }
}
