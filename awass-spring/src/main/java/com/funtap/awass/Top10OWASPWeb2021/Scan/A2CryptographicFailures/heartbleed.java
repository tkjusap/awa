package com.funtap.awass.Top10OWASPWeb2021.Scan.A2CryptographicFailures;


import java.io.*;

public class heartbleed {
    public void heartbleed(String domain) throws IOException {
        String command = "python /c start python ../../../pyModule/test.py";
        String prg = "import sys";
        BufferedWriter out = new BufferedWriter(new FileWriter("D:\\project\\awass-spring\\src\\main\\java\\com\\funtap\\awass\\pyModule\\test.py"));
        out.write(prg);
        out.close();
        Process p = Runtime.getRuntime().exec("D:\\project\\awass-spring\\src\\main\\java\\com\\funtap\\awass\\pyModule\\test.py");
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String ret = in.readLine();
        System.out.println("value is : "+ret);
    }
}
