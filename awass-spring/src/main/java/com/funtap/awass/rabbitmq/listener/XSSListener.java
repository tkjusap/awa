package com.funtap.awass.rabbitmq.listener;

import com.funtap.awass.Entity.UrlOb;
import com.funtap.awass.FunX.HttpCommon;
import com.funtap.awass.FunX.UpdateJob;
import com.funtap.awass.Top10OWASPWeb2021.Scan.A3Injection.XSS;
import com.funtap.awass.Top10OWASPWeb2021.Scan.A3Injection.xmlinjection;
import com.funtap.awass.jpaentity.ReportScan;
import com.funtap.awass.jpaentity.User;
import com.funtap.awass.jpaentity.elasticsearch.ReportScanLog;
import com.funtap.awass.rabbitmq.configuration.XMLinectionConfiguration;
import com.funtap.awass.security.JwtService;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.ImmediateRequeueAmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Configuration
public class XSSListener {
    @Autowired
    Gson gson;


    @RabbitListener(queues = XMLinectionConfiguration.QUEUE_XMLi,concurrency = "3")
    public void helloShipping(Message message) {
        try {
            UrlOb uri = gson.fromJson(new String(message.getBody()),UrlOb.class);
            XSS xss = new XSS();
            OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                    .build();
            boolean check = xss.Scan(uri);
            UUID uid = UUID.randomUUID();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:MM:ss");
            System.out.println(sdf.format(new Date())+" "+uid.toString()+" todo code sqli here " + new String(message.getBody()));
//            UpdateJob upjob = new UpdateJob();
//            upjob.updatejob(uri.getIdTarget(),-1);

            //done sub to
            Request request;
            Response response;
            do{
                request = new Request.Builder()
                        .url("http://localhost:26969/api/rabbit/update-job?id="+uri.getIdTarget()+"&total=-1")
                        .method("GET", null)
                        .build();
                response = client.newCall(request).execute();
            }while (!response.isSuccessful());
            System.err.println("update job");
            //report encd
            if(check) {
                ReportScanLog reportScan = new ReportScanLog();
                reportScan.setIdTarget(uri.getIdTarget());
                reportScan.setUrl(uri.getUrl());
                reportScan.setResult("Cross-Site Scripting (XSS)");
                reportScan.setLevel("HIGH");
                reportScan.setDes("A03:2021 – Injection");
                reportScan.setUsername(uri.getUsername());
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, gson.toJson(reportScan));
                request = new Request.Builder()
                        .url("http://localhost:26969/api/update-vul")
                        .method("POST", body)
                        .addHeader("Content-Type", "application/json")
                        .build();
                response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                    response.close();
                }
                String ouput = response.body().string();
                System.out.println(ouput);
            }

        } catch (ImmediateRequeueAmqpException e) {
            e.printStackTrace();
            throw new ImmediateRequeueAmqpException(e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AmqpRejectAndDontRequeueException("Error Handler converted exception to fatal", e);
        }
    }
}
