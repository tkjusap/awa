package com.funtap.awass.Top10OWASPWeb2021.Scan.A1BrokenAccessControl;

import com.funtap.awass.Entity.tree;
import com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A1BrokenAccessControl.Restrict_Folder_Access_sig;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Restrict_Folder_Access {
    private List<String> ListFolder = new ArrayList<>();

    public List<String>  Restrict_Folder_Access(List<tree> tree, String domain,String cookie) throws IOException {
        List<String> ListUrlVul = new ArrayList<>();
        ListFolder.add(domain);
        for (int i = 0; i <= ListFolder.size(); i++) {
            String x = "";
            try {
                x = ListFolder.get(i);
            } catch (Exception e) {
                break;
            }
            String exfather = x;
            if (!x.equals(domain))
                exfather = x.split("/")[List.of(x.split("/")).size() - 1];
            if (exfather.endsWith("/"))
                exfather = exfather.substring(0, exfather.length() - 1);
            if (x.endsWith("/"))
                x = x.substring(0, x.length() - 1);
            for (tree t : tree) {
                if (t.getFather().equals(exfather) && t.getChill().length() > 0) {
                    if (checkfolder(t.getChill())) {
                        if (!ListFolder.contains(x + t.getChill())) {
                            ListFolder.add(x + "/"+t.getChill());
                        }
                    }
                }
            }
        }

        for(String x : ListFolder){
            System.out.println("url="+x);
            if(check_Restrict_Folder_Access(x,cookie)){
                System.out.println("============> vul site : " + x);
                ListUrlVul.add(x);
            }
        }
        return ListUrlVul;
    }
    private boolean check_Restrict_Folder_Access(String url,String cookie) throws IOException {
        if (cookie != null){
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .followRedirects(true)
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .method("GET", null)
                    .addHeader("Cookie",cookie)
                    .build();
            Response response = client.newCall(request).execute();
            String html = response.body().string();
            html = html.replaceAll("\\<.*?>","");
            if(response.code()== 200){
                Restrict_Folder_Access_sig sig = new Restrict_Folder_Access_sig();
                String[] SIG = sig.getSIGs();
                for(String x : SIG){
                    if(!html.contains(x))
                        return false;
                }

                return true;
            }


        }
        else{
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .followRedirects(true)
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .method("GET", null)
                    .build();
            Response response = client.newCall(request).execute();
            String html = response.body().string();
            html = html.replaceAll("\\<.*?>","");
            if(response.code()== 200){
                Restrict_Folder_Access_sig sig = new Restrict_Folder_Access_sig();
                String[] SIG = sig.getSIGs();
                for(String x : SIG){
                    if(!html.contains(x))
                        return false;
                }

                return true;
            }
        }
        return  false;
    }
    private boolean checkfolder(String child) {
        if (child.contains(".")) {
            return false;
        }
        return true;
    }
}
