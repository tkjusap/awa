package com.funtap.awass.rabbitmq.listener;

import com.funtap.awass.Entity.UrlOb;
import com.funtap.awass.FunX.HttpCommon;
import com.funtap.awass.Top10OWASPWeb2021.Scan.A2CryptographicFailures.Text_Files_Accounts;
import com.funtap.awass.Top10OWASPWeb2021.Scan.A7IdentificationAndAuthenticationFailures.WeakPasswords;
import com.funtap.awass.jpaentity.elasticsearch.ReportScanLog;
import com.funtap.awass.rabbitmq.configuration.TextFilesAccountsConfiguration;
import com.funtap.awass.rabbitmq.configuration.WeakPasswordsConfiguration;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.ImmediateRequeueAmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class WeakPasswordsListener {
    @Autowired
    Gson gson;


    @RabbitListener(queues = WeakPasswordsConfiguration.QUEUE_weakPasswords,concurrency = "5")
    public void helloShipping(Message message) {
        try {
            UrlOb uri = gson.fromJson(new String(message.getBody()),UrlOb.class);
            WeakPasswords wp  = new WeakPasswords();
            OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                    .build();
            String cookie = null;
            String urlx = uri.getUrl();
            String domain = uri.getUrl().split("/")[2].trim();
            boolean check = wp.WeakPasswords(uri);
            UUID uid = UUID.randomUUID();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:MM:ss");
            System.out.println(sdf.format(new Date())+" "+uid.toString()+" todo vulns here " + new String(message.getBody()));

            //report
            if(check) {
                ReportScanLog reportScan = new ReportScanLog();
                reportScan.setIdTarget(uri.getIdTarget());
                reportScan.setUrl(uri.getUrl());
                reportScan.setResult("Weak Passwords");
                reportScan.setLevel("CRITICAL");
                reportScan.setDes("A07:2021-Identification and Authentication Failuress");
                reportScan.setUsername(uri.getUsername());
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, gson.toJson(reportScan));
                Request request = new Request.Builder()
                        .url("http://localhost:26969/api/update-vul")
                        .method("POST", body)
                        .addHeader("Content-Type", "application/json")
                        .build();
                Response response = client.newCall(request).execute();
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
