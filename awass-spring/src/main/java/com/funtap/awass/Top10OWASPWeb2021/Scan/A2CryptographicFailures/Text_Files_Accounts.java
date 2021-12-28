package com.funtap.awass.Top10OWASPWeb2021.Scan.A2CryptographicFailures;

import com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A2CryptographicFailures.Text_File;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Text_Files_Accounts {
    public void Text_Files_Accounts(String domain) throws IOException {
        Text_File tx = new Text_File();
        String[] InText = tx.getIntext();
        String intext = "";
        for (String x : InText) {
            intext = intext + "|" + x;
        }
        String query = "site:" + domain + " intitle:\"index of\" intext:\"" + intext + "\"";
        System.out.println(query);
        System.out.println(checkfile(query));
    }
    private boolean checkfile(String query) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .followRedirects(false)
                .build();
        Request request = new Request.Builder()
                .url("https://www.google.com/search?q=" + query)
                .method("GET", null)
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36")
                .addHeader("Sec-Ch-Ua-Platform", "\"Windows\"")
                .addHeader("Upgrade-Insecure-Requests", "1")
                .build();
        Response response = client.newCall(request).execute();
        String html = response.body().string();
        System.out.println(html);
        if (html.contains("role=\"text\"")) {
            return true;
        }
        return false;
    }
}
