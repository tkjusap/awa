package com.funtap.awass.Top10OWASPWeb2021.Scan.A1BrokenAccessControl;

import okhttp3.*;

import java.io.IOException;
public class Host_Header_Attack {
    public boolean GetHeaders(String url) throws IOException {
        try{
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .followRedirects(false)
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .method("GET", null)
                    .addHeader("Host", "localhost")
                    .build();
            Response response = client.newCall(request).execute();
            //response.code() == 200 is vul,!=200 is save
            if(response.code() == 200){
                return true;
            }

        }catch (Exception e){

        }
        return false;
    }

}
