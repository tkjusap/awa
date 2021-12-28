package com.funtap.awass.Top10OWASPWeb2021.Scan.A5SecurityMisconfiguration;

import com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A5SecurityMisconfiguration.Backup_And_Unreferenced_Files_M;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Backup_And_Unreferenced_File {
    public List<String> CheckBackupFile(String domain) throws IOException {
        String url = "";
        List<String> ListLink = new ArrayList<>();
        Backup_And_Unreferenced_Files_M BackUpFile = new Backup_And_Unreferenced_Files_M();
        BackUpFile.setDomain(domain);
        url = BackUpFile.getPayloa();
        System.out.println(url);
        String html = getHtml(url);
        System.out.println(html);
        ListLink = getHref(html, domain);
        for (int i = 0; i < ListLink.size(); i++) {
            if (!ListLink.get(i).contains(domain)) ;
            {
                ListLink.remove(i);
            }

        }
        System.out.println("=====================\n");
        System.out.println(ListLink);
        System.out.println("======================\n");
        return ListLink;
    }

    private String getHtml(String url) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .followRedirects(false)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("Host", "www.google.com.vn")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public List<String> getHref(String html, String domain) throws IOException {
        List listUrl = new ArrayList();
        Pattern p = Pattern.compile("href=[\\\"\\\']([^\\\"\\\']*)[\\\"\\\']");
        Matcher m = p.matcher(html);
        String url = "";
        while (m.find()) {
            url = m.group(0); // this variable should contain the link URL
            url = url.replaceAll("href=\"", "");
            url = url.replaceAll("href=\'", "");
            url = url.replaceAll("\">", "");
            url = url.replaceAll("\'>", "");
            url = url.replaceAll("\"", "");
            url = url.replaceAll("\'", "");
            url = url.trim();
            if (!url.contains("javascript")  && url.contains("/url?q=") && !url.contains("https://accounts.google.com/"))
                listUrl.add(url);
        }
        return listUrl;
    }
}
