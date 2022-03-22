package com.funtap.awass.api;

import com.funtap.awass.Entity.entityAPI;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A1BrokenObjectLevelAuthorization {
    public boolean scan(entityAPI obApi) {
        String reParam = "\\d*";
        String param = getTaget(obApi.getParam(), reParam);
        String htmlTest = getApi(obApi.getUrl(), null);
        String htmlTrain = getApi(obApi.getDomain() + obApi.getFolder() + param, null);
        System.out.println(htmlTest);
        System.out.println(htmlTrain);
        detectResult detect = new detectResult();
        if (detect.detect(htmlTrain, htmlTest)) {
            return true;
        }
        return false;
    }

    private String getTaget(String str, String regex) {
        String result = "";
        try {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(str);
            while (m.find()) {
                result = m.group(0);
                if (result.equals("")) {

                } else {
                    str = str.replaceAll(result, String.valueOf(Integer.valueOf(result) + 1));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return str;

    }

    private String getApi(String url, String cookie) {
        String html = "";
        OkHttpClient client = new OkHttpClient().newBuilder()
                .followRedirects(false)
                .build();
        Request request;
        Response response;
        try {
            if (cookie == null) {
                request = new Request.Builder()
                        .url(url)
                        .method("GET", null)
                        .addHeader("Sec-Fetch-Mode", "navigate")
                        .build();
                response = client.newCall(request).execute();

            } else {
                request = new Request.Builder()
                        .url(url)
                        .method("GET", null)
                        .addHeader("Cookie", cookie)
                        .build();
                response = client.newCall(request).execute();
            }
            html = response.body().string();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return html;
    }
}
