package com.funtap.awass.Top10OWASPWeb2021.Scan.A2CryptographicFailures;


import com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A2CryptographicFailures.Base64_Encoding_Secret;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Base64EncodeSecret {


    public Boolean Check_Base64(String cookie ){
        Base64_Encoding_Secret base64 = new Base64_Encoding_Secret();
        String payload_base64 = base64.getSIGBase64();
        Pattern value  = Pattern.compile(payload_base64);
        Matcher check_base64 = value.matcher(cookie);
//        if(check_base64.find())
//            System.out.println("cookie be encoding by base64");
//        else
//            System.out.println("cookie is safe");
        //match will return True,not match will return false
        return check_base64.find();
    }
}
