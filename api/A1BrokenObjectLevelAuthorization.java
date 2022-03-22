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
        List<String> listParam = new ArrayList<String>();
        listParam = obApi.getParam();
        String Param = "";
        for (String p : listParam) {
            String reparam = "=*\\d*";
            String temp = getTaget(p, reparam);
            System.out.println(temp);
            temp = temp.replaceAll("=", "");
            if (temp.length() > 0) {
                System.out.println(temp);
                if (Param.length() == 0) {
                    Param = String.valueOf((Integer.valueOf(temp) + 1));
                } else {
                    Param = Param + "&" + String.valueOf((Integer.valueOf(temp) + 1));
                }
            }
            System.out.println("==================" + temp);
        }
        String url = "";
        if (obApi.getUrl().contains("?")) {
            url = obApi.getDomain() + obApi.getFolder() + "?" + Param;
        } else {
            url = obApi.getDomain() + obApi.getFolder() + "/" + Param;
        }
        System.out.println(url);
        String train = getApi(obApi.getUrl(), null);
        String test = getApi(url, null);
        System.out.println(train);
        System.out.println(test);
        detectResult detect = new detectResult();
        if (detect.detect(train, test)) {
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
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;

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
                        .url("http://127.0.0.1:5000/id?id=1")
                        .method("GET", null)
                        .addHeader("Sec-Fetch-Mode", "navigate")
                        .build();
                response = client.newCall(request).execute();

            } else {
                request = new Request.Builder()
                        .url("http://127.0.0.1:5000/id?id=1")
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
