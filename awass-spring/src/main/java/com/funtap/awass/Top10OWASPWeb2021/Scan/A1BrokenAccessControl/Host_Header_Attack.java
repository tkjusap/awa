package com.funtap.awass.Top10OWASPWeb2021.Scan.A1BrokenAccessControl;

import com.funtap.awass.Entity.UrlOb;
import okhttp3.*;

import java.io.IOException;

public class Host_Header_Attack {
    public boolean GetHeaders(UrlOb ob, String cookies) throws IOException {
        String method = ob.getMethod();
        String param = ob.getParam();
        String url = ob.getUrl();

        try {
            if (cookies != null) {
                if (method.equals("GET")) {
                    OkHttpClient client = new OkHttpClient().newBuilder()
                            .build();
                    Request request = new Request.Builder()
                            .url((String) url)
                            .method("GET", null)
                            .addHeader("Cookie", cookies)
                            .build();
                    Response response = client.newCall(request).execute();
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
                    Response response = client.newCall(request).execute();
                }

            } else {
                if (method.equals("GET")) {
                    OkHttpClient client = new OkHttpClient().newBuilder()
                            .build();
                    Request request = new Request.Builder()
                            .url((String) url)
                            .method("GET", null)
                            .build();
                    Response response = client.newCall(request).execute();
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
                    Response response = client.newCall(request).execute();
                }
            }
        }catch(Exception e){

            }
            return false;
        }

    }
