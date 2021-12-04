///*
//package com.funtap.awass.Top10OWASPWeb2021.Scan.A1BrokenAccessControl;
//
//
//import com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A1BrokenAccessControl.LFI;
//import okhttp3.*;
//
//import java.io.IOException;
//
//public class Directory_Traversal_Files {
//
//    public Directory_Traversal_Files() {
//    }
//
//
//
//    public boolean Get_request_URL(String url) throws IOException {
//        LFI DTL = new LFI();
//        String[] payload = DTL.getArrPayLFI();
//
//    public boolean Get_request_URL() {
//        LFI lfi = new LFI();
//        String[] payload = lfi.getArrPayLFI();
//
//        for(String x : payload) {
//            OkHttpClient client = new OkHttpClient().newBuilder()
//                    .followRedirects(true)
//                    .build();
//            MediaType mediaType = MediaType.parse("text/plain");
//            Request request = new Request.Builder()
//                    .url(url+x)
//                    .method("GET", null)
//                    .build();
//            Response response = client.newCall(request).execute();
//            System.out.println(url + x + "  --> " + response.isRedirect()+ " :  " + response.code());
//            if(response.code()==200 && response.isRedirect() ==false){
//                System.out.println("this site have vul Traversal_Files");
//
//            }
//            else{
//                System.out.println("this site is safe");
//            }
//        }
//        return false;
//    }
//}
//*/
