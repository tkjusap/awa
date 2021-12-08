package com.funtap.awass.Start;

import com.funtap.awass.Entity.UrlOb;
import com.funtap.awass.SpiderWeb.SpiderWeb;
import com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A3Injection.SQLInjection;
import com.funtap.awass.Top10OWASPWeb2021.Scan.CheckVuln.ScanVulns;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

public class Start {
    public void StartGame() throws InterruptedException, IOException, URISyntaxException {
        Scanner scan = new Scanner(System.in);
        System.out.print("domain: ");
        //String url = scan.nextLine();
        String url = "http://testphp.vulnweb.com/";
        System.out.print("Cookie: ");
        //String cookie = scan.nextLine();
        String cookie = "";
        System.out.print("uname: ");
        //String uname = scan.nextLine();
        String uname = "";
        System.out.print("passwd: ");
        //String passwd = scan.nextLine();
        String passwd ="";
        System.out.print("Link login: ");
        //String linklogin = scan.nextLine();
        String linklogin = "";
        SpiderWeb spi = new SpiderWeb();
        if(url.trim().length() == 0){
            url = null;
        }
        if(cookie.trim().length() == 0){
            cookie = null;
        }
        if(linklogin.trim().length() == 0){
            linklogin = null;
        }
        if(uname.trim().length() == 0){
            uname = null;
        }
        if(passwd.trim().length() == 0){
            passwd = null;
        }

        List<UrlOb> list_url = spi.SpiderWeb(url, cookie, uname, passwd, linklogin);


//        ScanVulns scanv = new ScanVulns();
//        SQLInjection sqli = new SQLInjection();
//        for(UrlOb x : list_url){
//            //scanv.ScanVulns(sqli.getArrPaySQLin(), sqli.getArrSigSQLin(), x);
//            System.out.println(x.getUrl());
//            scanv.ScanVulcheck(x,cookie);
      //  }


        System.out.println("============End==============");

        System.out.println("============End ok test==============");
    }
}

