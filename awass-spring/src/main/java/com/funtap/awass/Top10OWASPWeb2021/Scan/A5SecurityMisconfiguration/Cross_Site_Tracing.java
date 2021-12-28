package com.funtap.awass.Top10OWASPWeb2021.Scan.A5SecurityMisconfiguration;

import com.funtap.awass.Entity.UrlOb;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class Cross_Site_Tracing {
    public boolean Cross_Site_Tracing(UrlOb OB, String cookie) {
        String url = OB.getUrl();
        if (OB.getMethod().equals("GET")) {
            if (trace(url, cookie)) {
                //System.out.println(url + " ===>>trace vul");
                return true;
            } else return false;
        }

        return false;
    }

    private boolean trace(String url, String cookie) {
        int code = 0;
        if (cookie != null) {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url((String) url)
                    .method("TRACE", null)
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            code = response.code();
            System.out.println(code);
            if (code == 200) {
                return true;
            } else {
                return false;
            }
        } else {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url((String) url)
                    .method("TRACE", null)
                    .build();
            boolean conn = true;
            Response response = null;
            while (conn) {
                try {
                    response = client.newCall(request).execute();
                    conn = false;
                } catch (SocketTimeoutException e) {
                    System.out.println("connecting ....");
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
            code = response.code();
            if (code == 200)
                return true;
            else
                return false;

        }

    }
}
