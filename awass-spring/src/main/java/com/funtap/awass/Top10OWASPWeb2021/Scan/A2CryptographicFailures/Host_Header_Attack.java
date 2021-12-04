package com.funtap.awass.Top10OWASPWeb2021.Scan.A2CryptographicFailures;

import okhttp3.*;

import java.io.IOException;
public class Host_Header_Attack {
    public int GetHeaders(String url) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .followRedirects(false)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("Host", "localhost")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.code());
        //response.code() == 200 is vul,!=200 is save
        return response.code();
    }

}
