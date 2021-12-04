package com.funtap.awass.FindSubdomain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class FindSubDomain {
    public List<String> FindSubdomain(String web  ) throws IOException {
        String url = "https://crt.sh/?q=";
        String domain = web ;
        List<String> ListDomain = crawl(url,domain) ;
        return ListDomain;
    }
    private List<String> crawl(String urls, String domain) throws IOException {
        // get html
        ArrayList<String> ListDomain = new ArrayList<String>();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://crt.sh/?q=" + domain)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        data = data.replaceAll("<BR>","\n ");
        //remove all char of html and url
        String noHTMLString = data.replaceAll("\\<.*?>","");
        noHTMLString = noHTMLString.replaceAll("www.","");
        // split to list string
        String[] listStr = noHTMLString.split("\n");
        // check the line contain domain
        for(String x : listStr){
            if(x.contains(domain)) {
                ListDomain.add(x);
            }
        }
        //return listdomain
        List listDomain = RemoveDuplicates(ListDomain);
        return listDomain;
    }
    private  List RemoveDuplicates(List listStr){
        List listDomain = new ArrayList<String>();
        for(int i = 0;i<listStr.size();i++){
            if ((i != 0) && ( i!= 1)){
                if (!listDomain.contains(listStr.get(i))){
                    listDomain.add(listStr.get(i));
                    System.out.println(listStr.get(i));
                }
            }
        }
        return listDomain;
    }
}
