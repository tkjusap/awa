package com.funtap.awass.rabbitmq.listener;

import com.funtap.awass.Entity.UrlOb;
import com.funtap.awass.FunX.HttpCommon;
import com.funtap.awass.Top10OWASPWeb2021.Scan.A1BrokenAccessControl.Directory_Traversal_Files;
import com.funtap.awass.Top10OWASPWeb2021.Scan.A5SecurityMisconfiguration.Backup_And_Unreferenced_File;
import com.funtap.awass.jpaentity.ReportScan;
import com.funtap.awass.jpaentity.User;
import com.funtap.awass.jpaentity.elasticsearch.ReportScanLog;
import com.funtap.awass.rabbitmq.configuration.BackupAndUnreferencedFileConfiguration;
import com.funtap.awass.rabbitmq.configuration.DirectoryTraversalFilesRabbitConfiguration;
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
import java.util.List;
import java.util.UUID;

@Configuration
public class BackupAndUnreferencedFileListener {
    @Autowired
    Gson gson;


    @RabbitListener(queues = BackupAndUnreferencedFileConfiguration.QUEUE_Backup,concurrency = "1")
    public void helloShipping(Message message) {
        try {
            UrlOb uri = gson.fromJson(new String(message.getBody()),UrlOb.class);
            Backup_And_Unreferenced_File bak = new Backup_And_Unreferenced_File();
            OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                    .build();
            String cookie = null;
            String domain = uri.getUrl().split("/")[2].trim();
            List<String> listx = bak.CheckBackupFile(domain);
            boolean check = false;
            if(listx == null){
                check = false;
            }else{
                check = true;
            }
            UUID uid = UUID.randomUUID();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:MM:ss");
            System.out.println(sdf.format(new Date())+" "+uid.toString()+" todo vulns here " + new String(message.getBody()));

            //report
            if(check) {
                ReportScanLog reportScan = new ReportScanLog();
                reportScan.setIdTarget(uri.getIdTarget());
                reportScan.setUrl(uri.getUrl());
                reportScan.setResult("Backup And Unreferenced File");
                reportScan.setLevel("CRITICAL");
                reportScan.setDes("A05:2021 – Security Misconfiguration");
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
