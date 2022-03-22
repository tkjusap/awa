package com.funtap.awass;

import com.funtap.awass.SpiderWeb.SpiderWeb;
import com.funtap.awass.api.api;
import com.funtap.awass.api.detectResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) throws IOException {

        SpiderWeb sp = new SpiderWeb();
        //System.out.println(sp.detectJsSite("https://www.youtube.com/watch?v=jr64cYqId7Q&ab_channel=%C4%90TNguy%E1%BB%85n%5BH%E1%BB%8DcExcelOnline%5D"));;
        //System.out.println(sp.SpiderWeb("http://testphp.vulnweb.com/",null,null,null,null,null));;
        //System.out.println(sp.detectJsSite("http://testphp.vulnweb.com/"));;
        String url = "http://127.0.0.1:5000/id?id=1&a=1";
        String url1 = "http://127.0.0.1:5000/id?id=3";
        api api = new api();
        api.getapi(url);
    }
}
