package com.funtap.awass.Start;

import com.funtap.awass.Entity.UrlOb;
import com.funtap.awass.SpiderWeb.SpiderWeb;
import com.funtap.awass.SpiderWeb.get_tree;
import com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A3Injection.SQLInjection;
import com.funtap.awass.Top10OWASPWeb2021.Scan.A2CryptographicFailures.heartbleed;
import com.funtap.awass.Top10OWASPWeb2021.Scan.A6VulnerableandOutdatedComponents.PHP_Eval_Function;
import com.funtap.awass.Top10OWASPWeb2021.Scan.CheckVuln.ScanVulns;
import com.funtap.awass.python.pythonInjava;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
        String uname = "test";
        System.out.print("passwd: ");
        //String passwd = scan.nextLine();
        String passwd ="test";
        System.out.print("Link login: ");
        //String linklogin = scan.nextLine();
        String linklogin = "http://testphp.vulnweb.com/login.php";
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

//        List<UrlOb> list_url = spi.SpiderWeb(url, cookie, uname, passwd, linklogin);
////
////        ScanVulns scanv = new ScanVulns();
////        SQLInjection sqli = new SQLInjection();
//        for (UrlOb x : list_url) {
////            //scanv.ScanVulns(sqli.getArrPaySQLin(), sqli.getArrSigSQLin(), x);
////            System.out.println(x.getUrl());
////            scanv.ScanVulcheck(x, cookie);
//            PHP_Eval_Function php = new PHP_Eval_Function();
//
//            System.out.println(php.PHP_Eval_Funtion(x,cookie));
//        }

        test("");

        System.out.println("============End==============");

        System.out.println("============End ok test==============");
    }
    private void test(String domain) throws IOException, InterruptedException {
//        //tree
//        String[] ListUrl;
//        ListUrl =new String[]{"http://testphp.vulnweb.com/", "http://testphp.vulnweb.com/style.css", "http://testphp.vulnweb.com/search.php", "http://testphp.vulnweb.com/index.php", "http://testphp.vulnweb.com/categories.php", "http://testphp.vulnweb.com/artists.php", "http://testphp.vulnweb.com/disclaimer.php", "http://testphp.vulnweb.com/cart.php", "http://testphp.vulnweb.com/guestbook.php", "http://testphp.vulnweb.com/AJAX/index.php", "http://testphp.vulnweb.com/login.php", "http://testphp.vulnweb.com/userinfo.php", "http://testphp.vulnweb.com/privacy.php", "http://testphp.vulnweb.com/Mod_Rewrite_Shop/", "http://testphp.vulnweb.com/hpp/", "http://testphp.vulnweb.com/images/logo.gif", "http://testphp.vulnweb.com/Flash/add.swf", "http://testphp.vulnweb.com/showimage.php", "http://testphp.vulnweb.com/product.php", "http://testphp.vulnweb.com/artists.php", "http://testphp.vulnweb.com/listproducts.php", "http://testphp.vulnweb.com/showimage.php", "http://testphp.vulnweb.com/artists.php#", "http://testphp.vulnweb.com/", "http://testphp.vulnweb.com/images/remark.gif", "http://testphp.vulnweb.com/AJAX/styles.css", "http://testphp.vulnweb.com/AJAX/index.php#", "http://testphp.vulnweb.com/showimage.php", "http://testphp.vulnweb.com/userinfo.php", "http://testphp.vulnweb.com/signup.php", "http://testphp.vulnweb.com/Mod_Rewrite_Shop/Details/network-attached-storage-dlink/1/", "http://testphp.vulnweb.com/Mod_Rewrite_Shop/Details/web-camera-a4tech/2/", "http://testphp.vulnweb.com/Mod_Rewrite_Shop/Details/color-printer/3/", "http://testphp.vulnweb.com/Mod_Rewrite_Shop/images/1.jpg", "http://testphp.vulnweb.com/Mod_Rewrite_Shop/images/2.jpg", "http://testphp.vulnweb.com/Mod_Rewrite_Shop/images/3.jpg", "http://testphp.vulnweb.com/hpp/", "http://testphp.vulnweb.com/listproducts.php", "http://testphp.vulnweb.com/listproducts.php#", "http://testphp.vulnweb.com/secured/newuser.php", "http://testphp.vulnweb.com/Mod_Rewrite_Shop/BuyProduct-1/", "http://testphp.vulnweb.com/Mod_Rewrite_Shop/RateProduct-1.html", "http://testphp.vulnweb.com/Mod_Rewrite_Shop/BuyProduct-2/", "http://testphp.vulnweb.com/Mod_Rewrite_Shop/RateProduct-2.html", "http://testphp.vulnweb.com/Mod_Rewrite_Shop/BuyProduct-3/", "http://testphp.vulnweb.com/Mod_Rewrite_Shop/RateProduct-3.html", "http://testphp.vulnweb.com/hpp/params.php", "http://testphp.vulnweb.com/hpp/params.php"};
//        get_tree t = new get_tree();
//        t.get_tree(ListUrl,domain);
        //pyjava
        heartbleed heart = new heartbleed();
        heart.heartbleed("");
    }
}

