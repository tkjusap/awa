package com.funtap.awass.rabbitmq.listener;

import com.funtap.awass.Entity.UrlOb;
import com.funtap.awass.FunX.HttpCommon;
import com.funtap.awass.FunX.UpdateJob;
import com.funtap.awass.Top10OWASPWeb2021.Scan.A1BrokenAccessControl.Directory_Traversal_Files;
import com.funtap.awass.Top10OWASPWeb2021.Scan.A8SoftwareandDataIntegrityFailuress.Insecure_Deserialization;
import com.funtap.awass.jpaentity.ReportScan;
import com.funtap.awass.jpaentity.User;
import com.funtap.awass.jpaentity.elasticsearch.ReportScanLog;
import com.funtap.awass.rabbitmq.configuration.DirectoryTraversalFilesRabbitConfiguration;
import com.funtap.awass.rabbitmq.configuration.InsecureDeserializationConfiguration;
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
public class InsecureDeserializationListener {
    @Autowired
    Gson gson;


    @RabbitListener(queues = InsecureDeserializationConfiguration.QUEUE_ID,concurrency = "3")
    public void helloShipping(Message message) {
        try {
            UrlOb uri = gson.fromJson(new String(message.getBody()),UrlOb.class);
            Insecure_Deserialization id = new Insecure_Deserialization();
            OkHttpClient client = HttpCommon.getInstance().getHttpClient().newBuilder()
                    .build();
            String cookie = null;
            boolean check = id.Insecure_Deserialization(uri, cookie);
            UUID uid = UUID.randomUUID();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:MM:ss");
            System.out.println(sdf.format(new Date())+" "+uid.toString()+" todo vulns here " + new String(message.getBody()));

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
            //report
            if(check) {
                ReportScanLog reportScan = new ReportScanLog();
                reportScan.setIdTarget(uri.getIdTarget());
                reportScan.setUrl(uri.getUrl());
                reportScan.setResult("Insecure Deserialization");
                reportScan.setLevel("MEDIUM");
                reportScan.setDes("A08:2021 – Software and Data Integrity Failures");
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
