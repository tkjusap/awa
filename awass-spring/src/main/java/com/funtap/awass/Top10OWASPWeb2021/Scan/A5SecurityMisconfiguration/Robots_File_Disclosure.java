package com.funtap.awass.Top10OWASPWeb2021.Scan.A5SecurityMisconfiguration;

import com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A5SecurityMisconfiguration.robot_file_modul;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Robots_File_Disclosure {
    public List<String>  Robots_File_Disclosure(String domain) throws IOException {
        String User_agent = "";
        List<String> Allow = new ArrayList<String>();
        List<String> Disallow = new ArrayList<String>();
        List<String> ListString = new ArrayList<String>();
        List<String> ListuserAgnt = new ArrayList<String>();
        List<String> Listurl = new ArrayList<String>();

        List<robot_file_modul> ListRobot = new ArrayList<robot_file_modul>();
        String robot = haveRobots(domain);
        System.out.println("================robots================");
        boolean add = false;
        if (robot.length() > 0) {
            ListuserAgnt = List.of(robot.split("User-agent"));
            for (String UserAgent : ListuserAgnt) {
                UserAgent = "User-agent" + UserAgent;
                ListString = List.of(UserAgent.split("\n"));
                for (String x : ListString) {
                    if (x.contains("User-agent")) {
                        try {
                            User_agent = x.split(":")[1].trim();
                        } catch (Exception e) {
                            User_agent = null;
                        }
                    }
                    if (x.contains("Allow")) {
                        try {
                            Allow.add(x.split(":")[1].trim());
                        } catch (Exception e) {
                            Allow.add(null);
                        }
                    }
                    if (x.contains("Disallow")) {
                        try {
                            Disallow.add(x.split(":")[1].trim());
                        } catch (Exception e) {
                            Disallow.add(null);
                        }
                    }
                }
                robot_file_modul rbm = new robot_file_modul(User_agent, Allow, Disallow);
                ListRobot.add(rbm);
                User_agent = "";
                Allow = new ArrayList<String>();
                Disallow = new ArrayList<String>();
            }
            for (robot_file_modul x : ListRobot) {
                int code = 0;
                if (x.getDisallow().size() > 0) {
                    if (x.getUser_agent().contains("*")) {
                        Disallow = x.getDisallow();
                        for (String paths : Disallow) {
                            paths = domain + paths;
                            code = checkStatusCode(paths);
                            if (code == 200) {
                                System.out.println("error in " + paths);
                                Listurl.add(paths);
                            }
                        }
                    }
                }
            }
        }
        return Listurl;
    }

    private boolean checkDisallow(robot_file_modul rbm) {
        List<String> Disallow = new ArrayList<String>();
        int status = 0;
        Disallow = rbm.getDisallow();

        return false;
    }

    private int checkStatusCode(String url) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .followRedirects(false)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        return response.code();
    }

    private String haveRobots(String domain) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(domain + "/robots.txt")
                .method("GET", null)
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36")
                .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .addHeader("Connection", "close")
                .build();
        Response response = client.newCall(request).execute();
        String html = response.body().string();
        if (response.code() == 200) {
            return html;
        }
        return "";
    }
}
