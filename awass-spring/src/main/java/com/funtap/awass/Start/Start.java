package com.funtap.awass.Start;

import com.funtap.awass.SpiderWeb.SpiderWeb;

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

        List<String> list_url = (List<String>) spi.SpiderWeb(url, cookie, uname, passwd, linklogin);


        System.out.println("============End==============");
    }
}

