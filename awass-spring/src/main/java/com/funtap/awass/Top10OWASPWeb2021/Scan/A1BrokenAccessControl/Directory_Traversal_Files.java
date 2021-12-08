package com.funtap.awass.Top10OWASPWeb2021.Scan.A1BrokenAccessControl;

import com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A1BrokenAccessControl.LFI;
import okhttp3.*;

import java.io.IOException;
import java.util.Locale;

public class Directory_Traversal_Files {
    public boolean Get_request_URL(String url) throws IOException {
        LFI lfi = new LFI();
        String[] payload = lfi.getArrPayLFI();
        String[] Sig = lfi.getArrSigLFI();

        for (String x : payload) {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .followRedirects(true)
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            Request request = new Request.Builder()
                    .url(url + x)
                    .method("GET", null)
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println(url + x + "  --> " + response.isRedirect() + " :  " + response.code());
            for (String signature : Sig) {
                if (response.code()==200) {
                    return true;
                } else {
                }
            }
        }
        return false;
    }
}
