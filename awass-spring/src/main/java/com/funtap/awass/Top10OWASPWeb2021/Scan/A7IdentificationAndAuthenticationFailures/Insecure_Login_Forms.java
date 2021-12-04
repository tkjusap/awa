package com.funtap.awass.Top10OWASPWeb2021.Scan.A7IdentificationAndAuthenticationFailures;

import okhttp3.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Insecure_Login_Forms {
    private String cookie = "security_level=0; PHPSESSID=fa1qai1j7n0bft89ngunl14336";

    public Insecure_Login_Forms() {
    }

    ;

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public boolean Insecure_Login_Forms(String url) throws IOException {
        Map<String, String> ListFomr = new HashMap<String, String>();
        String html = GetHtmlString(url);
        ListFomr = getForm(html);
        System.out.println(ListFomr);
        //login
        int logincode = GetPostMethod(ListFomr, url);
        //test status login with bwapp ,this is signature of site when Successful login,change it
        //we will get the username,password
        if (logincode == 302) {
            //System.out.println("this site Insecure Login Forms");
        } else {
            //System.out.println("this site is safe");
        }
        return false;
    }

    private List<String> get_name_param(String html) {
        List<String> listparam = new ArrayList<>();
        Pattern userp = Pattern.compile("name=\"([^\"]*)\"");
        Pattern value = Pattern.compile("value=\"([^\"]*)\"");
        Matcher param = userp.matcher(html);
        Matcher param1 = value.matcher(html);
        while (param.find()) {
            listparam.add(param.group(1));
        }
        while (param1.find()) {
            listparam.add(param1.group(1));
        }
        System.out.println(listparam);
        return listparam;
    }

    private Map<String, String> getForm(String html) throws IOException {
        html = html.toLowerCase(Locale.ROOT);
        Map<String, String> ListFomr = new HashMap<String, String>();
        Pattern fontPattern = Pattern.compile("<font(.*?>)(.*.)<\\/font>");
        List Listhtml = List.of(html.split("\n"));
        html = "";
        int B = 1;
        String user = "";
        String pass = "";
        boolean continues = true;
        boolean add = false;
        int index = 0;
        while (continues) {
            String Indexhtml = (String) Listhtml.get(index);
            user = "";
            pass = "";
            if ((Indexhtml.contains("<form") && Indexhtml.contains("login")) || add) {
                add = true;
                html = html + "\n" + Indexhtml;
            }
            if (Indexhtml.contains("</form>")) {
                continues = false;
            }
            index = index + 1;
        }
        List<String> listparam = get_name_param(html);
        Matcher fontP = fontPattern.matcher(html);
        int i = 0;
        while (fontP.find()) {
            B = 1 ^ B;
            if (B == 1) {
                user = fontP.group(2);
                System.out.println(fontP.group());
                ListFomr.put(listparam.get(i), user);
                i++;
            } else {
                pass = fontP.group(2);
                ListFomr.put(listparam.get(i), pass);
                i++;
            }

        }
        ListFomr.put(listparam.get(listparam.size() - 2), listparam.get(listparam.size() - 1));
        return ListFomr;
    }


    private int GetPostMethod(Map<String, String> ListDataFormBodyString, String url) throws IOException {
        List<String> ListElemen = new ArrayList<String>();
        Map<String, String> ListFomr = new HashMap<String, String>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        FormBody.Builder formBuilder = new FormBody.Builder();
        //add data to body
        for (Map.Entry<String, String> x : ListDataFormBodyString.entrySet()) {
            formBuilder.add(x.getKey(), String.valueOf(x.getValue()));
        }
        RequestBody formBody = formBuilder.build();
        System.out.println(formBody.toString());
        Request request = new Request.Builder()
                .url(url)
                .method("POST", formBody)
                .build();
        Response response = client.newCall(request).execute();
        return response.code();
    }

    public String GetHtmlString(String url) throws IOException {
        String html = "";
        OkHttpClient client = new OkHttpClient().newBuilder()
                .followRedirects(false)
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Cookie", cookie)
                .build();
        Response response = client.newCall(request).execute();
        html = response.body().string();
        return html;
    }
}