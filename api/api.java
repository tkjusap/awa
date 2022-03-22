package com.funtap.awass.api;

import com.funtap.awass.Entity.entityAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class api {
    public void getapi(String url) throws IOException {
        entityAPI obApi = new entityAPI();
        obApi = analysicAPI (url);

        A1BrokenObjectLevelAuthorization a1 = new A1BrokenObjectLevelAuthorization();

        System.out.println(a1.scan(obApi));
    }

    private entityAPI analysicAPI(String url) {
        String reDomain = "https*:\\/\\/[^\\/]*";
        String reFolder = "[^?]*";
        entityAPI obApi = new entityAPI();
        String domain =getTaget(url,reDomain);
        String reParam = "\\?[^\\s]*";
        String Param ;
        String folder = "";
        if (url.contains("?")) {
            Param = getTaget(url,reParam);
            folder = getListTaget(url.replaceAll(domain,""), reFolder).get(0);
            obApi = new entityAPI(url,domain,folder,Param);
        }
        return obApi;
    }

    private String getTaget(String url, String regex) {
        String result = "";
        try {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(url);
            while (m.find()) {
                result = m.group(0);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;

    }
    private List<String> getListTaget(String url, String regex) {
        List<String> listResult = new ArrayList<String>();
        String result = "";
        try {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(url);
            while (m.find()) {
                result = m.group(0);
                listResult.add(result);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listResult;

    }
}
