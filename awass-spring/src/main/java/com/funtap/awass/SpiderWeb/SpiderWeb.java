package com.funtap.awass.SpiderWeb;

import com.funtap.awass.Entity.UrlOb;
import okhttp3.*;


import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpiderWeb {
    private List<UrlOb> ListAllUrl = new ArrayList<>();

    public List<UrlOb> SpiderWeb(String web, String cookie, String User, String Pass, String linkLogin) throws IOException, URISyntaxException, InterruptedException {


        List<String> Listpaths = new ArrayList<String>();
        //get html web
        String html = GetHtmlString(web, cookie);
        //check login
        login login = new login();
        UrlOb Ob = new UrlOb(web, "GET", null, 0, false);
        ListAllUrl.add(Ob);
        if (web != null && cookie == null && linkLogin == null && User == null && Pass == null) {
            System.out.println("==== start job 1 ====");
            //get url
            spider(cookie, 3, web);
        } else if (web != null && cookie != null && linkLogin == null && User == null && Pass == null) {
            System.out.println("==== Start job 2 =====");
            //get url
            spider(cookie, 3, web);
        } else if (web != null && cookie == null && linkLogin != null && User != null && Pass != null) {
            System.out.println("==== Start job 3 =====");
            cookie = login.getCookie(linkLogin, User, Pass);
            //get url
            spider(cookie, 3, web);
        } else {
            System.out.println("==== Error input =====");
        }


        return ListAllUrl;
    }

    private void spider(String cookie, int depth, String web) throws IOException, URISyntaxException, InterruptedException {
        List<String> a = new ArrayList<>();
        for (int i = 0; i < depth; i++) {
            int size = ListAllUrl.size();
            for (int j = 0; j < size; j++) {
                UrlOb Ob = (UrlOb) ListAllUrl.get(j);
                if (Ob.isScan() == false) {
                    getObjectByUrl(Ob, cookie, web);
                    Ob.setScan(true);
                }
            }

        }
        System.out.println("===========size " + ListAllUrl.size() + "=================");

        System.out.println("================show result ===================");
        for (UrlOb x : ListAllUrl) {
            a.add("\""+x.getUrl()+"\"");
        }
        System.out.println(a);
    }

    private void getObjectByUrl(UrlOb Ob, String cookie, String web) throws InterruptedException, IOException, URISyntaxException {

        String url = "";
        String method = "";
        String param = null;
        int depth = Ob.getDepth() + 1;
        String html = "";
        //get out key
        if (!CheckLogOutKeyWord(Ob.getUrl())) {
            if (Ob.getMethod() == "GET") {
                html = GetHtmlString(Ob.getUrl() + "?" + Ob.getParam(), cookie);
            } else {
                html = PostHtmlString(Ob.getUrl(), Ob.getParam(), cookie);
            }
            List<String> ListUrl = getHref(html);
            var linkBase = new URI(Ob.getUrl());
            for (String urlNotVerify : ListUrl) {
                var linkHight2 = new URI(urlNotVerify);
                url = String.valueOf(linkBase.resolve(linkHight2)).split("\\?")[0];
                method = "GET";
                try {
                    param = String.valueOf(linkBase.resolve(linkHight2)).split("\\?")[1];
                } catch (Exception e) {
                    param = null;
                }

                UrlOb UrlObject = new UrlOb(url, method, param, depth, false);
                if (CheckObject(UrlObject, web) && CheckDup(UrlObject, web)) {
                    ListAllUrl.add(UrlObject);
                }
                //add post method form
                List<String> ListForm = GetForm(html);
                for (String form : ListForm) {
                    param = GetParamFrom(form);
                    method = GetMethodInForm(form);
                    linkHight2 = new URI(GetActionInForm(form));
                    url = String.valueOf(linkBase.resolve(linkHight2)).split("\\?")[0];
                    UrlObject = new UrlOb(url, method, param, depth, false);
                    if (CheckObject(UrlObject, web) && CheckDup(UrlObject, web)) {
                        ListAllUrl.add(UrlObject);
                    }
                }
            }
        }
    }

    private boolean CheckObject(UrlOb Ob, String domain) {
        String url = Ob.getUrl();
        String param = Ob.getParam();

        if (Ob.getUrl().contains(domain)) {
            return true;
        } else {
            return false;
        }

    }

    private boolean CheckDup(UrlOb Ob, String domain) {
        String url = Ob.getUrl();
        String param = Ob.getParam();
        for (int i = 0; i < ListAllUrl.size(); i++) {
            UrlOb xUrl = (UrlOb) ListAllUrl.get(i);
            if(xUrl.getUrl().equals(url)){
                if(analysisParam(xUrl.getParam()).equals(analysisParam(param)))
                    return false;

            }
        }
        return true;
    }

    public String analysisParam(String param) {
        if(param!=null){
            String fomatparam = "";
            String folder = "";
            List<String> ListParam = new ArrayList<String>();
            Pattern folderRE = Pattern.compile(".*\\/");
            Matcher folderTag = folderRE.matcher(param);
            while (folderTag.find()) {
                folder = folderTag.group(0);
            }
            fomatparam = fomatparam + folder;
            param = param.replaceAll(folder, "");
            ListParam = List.of(param.split("\\&"));
            for (int i = 0; i < ListParam.size(); i++) {
                String x = ListParam.get(i);
                if (checkFile(x)) {
                    String firstParam = x.split("\\.")[1];
                    if (i != 0) {
                        fomatparam = fomatparam + "&x." + firstParam;
                    } else {
                        fomatparam = fomatparam + "x." + firstParam;
                    }
                } else if (x.contains("=")) {
                    String firstParam = x.split("=")[0];
                    if (i != 0) {
                        fomatparam = fomatparam + "&" + firstParam + "=y";
                    } else {
                        fomatparam = fomatparam + firstParam + "=y";
                    }
                }
            }
            return fomatparam;
        }
        else
            return "";
    }

    private boolean checkFile(String param) {
        if (param.toLowerCase(Locale.ROOT).contains(".jpg")
                || param.toLowerCase(Locale.ROOT).contains(".png")
                || param.toLowerCase(Locale.ROOT).contains(".js")
                || param.toLowerCase(Locale.ROOT).contains(".doc")
                || param.toLowerCase(Locale.ROOT).contains(".xml")
                || param.toLowerCase(Locale.ROOT).contains(".gif")
                || param.toLowerCase(Locale.ROOT).contains(".json")
                || param.toLowerCase(Locale.ROOT).contains(".css")
        ) {
            return true;
        } else {
            return false;
        }
    }

    private String GetHtmlString(String url, String cookie) throws IOException {
        String html = "";
        if (cookie != null) {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url((String) url)
                    .method("GET", null)
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
                }
            }
            html = response.body().string();
        } else {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url((String) url)
                    .method("GET", null)
                    .build();
            boolean conn = true;
            Response response = null;
            while (conn) {
                try {
                    response = client.newCall(request).execute();
                    conn = false;
                } catch (SocketTimeoutException e) {
                    System.out.println("connecting ....");
                }
            }
            html = response.body().string();
        }


        return html;
    }

    private List<String> GetForm(String html) {
        List<String> ListForm = new ArrayList<String>();
        List<String> Listhtml = List.of(html.split("\n"));
        boolean continues = true;
        boolean add = false;
        int index = 0;
        html = "";
        while (continues) {
            String Indexhtml = (String) Listhtml.get(index);
            if ((Indexhtml.contains("<form")
                    && !Indexhtml.contains("sigout")
                    && !Indexhtml.contains("logout")
                    && !Indexhtml.contains("dangxuat")
                    && !Indexhtml.contains("thoat")
                    && !Indexhtml.contains("sing-out")
                    && !Indexhtml.contains("log-out")
                    && !Indexhtml.contains("dang-xuat")) || add) {
                add = true;
                html = html + "\n" + Indexhtml;
            }
            if (Indexhtml.contains("</form>")) {
                add = false;
                if (html.length() > 0) {
                    ListForm.add(html);
                }
                html = "";

            }
            index = index + 1;
            if (index == Listhtml.size()) {
                break;
            }
        }
        return ListForm;
    }

    private String PostHtmlString(String url, String param, String cookie) throws IOException {
        if (cookie != null) {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .followRedirects(false)
                    .build();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, param);
            Request request = new Request.Builder()
                    .url(url)
                    .method("POST", body)
                    .addHeader("Cookie", cookie)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } else {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .followRedirects(false)
                    .build();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, param);
            Request request = new Request.Builder()
                    .url(url)
                    .method("POST", body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }
    }

    private String GetParamFrom(String Form) {
        String name = "";
        String value = "";
        String param = "";
        Pattern nameRe = Pattern.compile("name=\"([^\"]*)\"");
        Pattern valueRE = Pattern.compile("value=\\\"([^\\\"]*)\\\"");
        Pattern inputRE = Pattern.compile("<input(.*)\"([^>]*)\\>");
        List<String> ListParam = new ArrayList<String>();
        //get  in put tag
        Matcher inputTag = inputRE.matcher(Form);
        while (inputTag.find()) {
            name = "";
            value = "";
            Matcher nameTag = nameRe.matcher(inputTag.group(0));
            Matcher valueTag = valueRE.matcher(inputTag.group(0));
            while (nameTag.find()) {
                name = nameTag.group(1);
            }
            while (valueTag.find()) {
                value = valueTag.group(1);
            }
            if (name!="" && value == "" ) {
                value = "a";
                ListParam.add(name + "=" + value);
            }
            else if(name!="" && value != ""){
                ListParam.add(name + "=" + value);
            }
        }
        for (int i = 0; i < ListParam.size(); i++) {
            if (i == 0) {
                param = ListParam.get(i);
            } else {
                param = param + "&" + ListParam.get(i);
            }
        }
        return param;
    }

    private String GetActionInForm(String Form) {
        String action = "";

        Pattern actionRE = Pattern.compile("action=\"([^\"]*)\"");
        List<String> ListParam = new ArrayList<String>();
        //get  in put tag
        Matcher actionTag = actionRE.matcher(Form);
        while (actionTag.find()) {
            action = actionTag.group(1);
        }
        return action;
    }

    public String GetMethodInForm(String Form) {
        String method = "";

        Pattern methodRE = Pattern.compile("method=\"([^\"]*)\"");
        List<String> ListParam = new ArrayList<String>();
        //get  in put tag
        Matcher methodTag = methodRE.matcher(Form);
        while (methodTag.find()) {
            method = methodTag.group(1);
        }
        return method.toUpperCase(Locale.ROOT);
    }

    private boolean CheckLogOutKeyWord(String url) {
        if (url.toLowerCase(Locale.ROOT).contains("sigout")
                || url.toLowerCase(Locale.ROOT).contains("logout")
                || url.toLowerCase(Locale.ROOT).contains("dangxuat")
                || url.toLowerCase(Locale.ROOT).contains("thoat")
                || url.toLowerCase(Locale.ROOT).contains("sing-out")
                || url.toLowerCase(Locale.ROOT).contains("log-out")
                || url.toLowerCase(Locale.ROOT).contains("dang-xuat")) {
            return true;
        } else {
            return false;
        }
    }

    public List<String> getHref(String html) throws IOException {
        List listUrl = new ArrayList();
        Pattern p = Pattern.compile("href=[\\\"\\\']([^\\\"\\\']*)[\\\"\\\']");
        Pattern p2 = Pattern.compile("src=[\\\"\\\']([^\\\"\\\']*)[\\\"\\\']");
        Matcher m = p.matcher(html);
        Matcher m2 = p2.matcher(html);
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
            if (!url.contains("javascript"))
                listUrl.add(url);
        }
        while (m2.find()) {
            url = m2.group(1); // this variable should contain the link URL
            url = url.replaceAll("src=\"", "");
            url = url.replaceAll("src=\'", "");
            url = url.replaceAll("\">", "");
            url = url.replaceAll("\'>", "");
            url = url.replaceAll("\"", "");
            url = url.replaceAll("\'", "");
            url = url.trim();
            listUrl.add(url);
        }
        return listUrl;
    }


}

