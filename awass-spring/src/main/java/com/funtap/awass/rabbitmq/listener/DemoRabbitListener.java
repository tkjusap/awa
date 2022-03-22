package com.funtap.awass.rabbitmq.listener;

import com.funtap.awass.Entity.UrlOb;
import com.funtap.awass.FunX.UpdateJob;
import com.funtap.awass.Model.TargetModel;
import com.funtap.awass.SpiderWeb.SpiderWeb;
import com.funtap.awass.Top10OWASPWeb2021.InformationGathering.InfoGathering;
import com.funtap.awass.rabbitmq.configuration.*;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.ImmediateRequeueAmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.funtap.awass.rabbitmq.configuration.DemoRabbitConfiguration.QUEUE_HELLO;

@Configuration
public class DemoRabbitListener {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    Gson gson;
    @Autowired
    OkHttpClient client;
    @RabbitListener(queues = QUEUE_HELLO,concurrency = "1")
    public void helloShipping(Message message) {
        try {
            TargetModel targetModel = gson.fromJson(new String(message.getBody()),TargetModel.class);
            SpiderWeb spi = new SpiderWeb();
            List<UrlOb> list_url = spi.SpiderWeb(targetModel.getUrl(), targetModel.getCookie(), targetModel.getUname(), targetModel.getPasswd(), targetModel.getLinklogin(), targetModel.getUsername());
            targetModel.setCookie(spi.cookie_web);
            int totalTask = 0;
            UrlOb domainx = list_url.get(0);
            domainx.setUsername(targetModel.getUsername());
            domainx.setIdTarget(targetModel.getIdTarget());
//            InfoGathering infogath = new InfoGathering();
//            infogath.Gathering(domainx.getUrl(), domainx.getIdTarget(), domainx.getUsername());
//            rabbitTemplate.convertAndSend(InfoGatheringConfiguration.EXCHANGE_Info, InfoGatheringConfiguration.ROUTING_KEY, gson.toJson(domainx));
            rabbitTemplate.convertAndSend(BackupAndUnreferencedFileConfiguration.EXCHANGE_Backup, BackupAndUnreferencedFileConfiguration.ROUTING_KEY, gson.toJson(domainx));
            rabbitTemplate.convertAndSend(RestrictFolderAccessConfiguration.EXCHANGE_Res, RestrictFolderAccessConfiguration.ROUTING_KEY, gson.toJson(domainx));
//            rabbitTemplate.convertAndSend(RobotsFileDisclosureConfiguration.EXCHANGE_Robot, RobotsFileDisclosureConfiguration.ROUTING_KEY, gson.toJson(domainx));
            rabbitTemplate.convertAndSend(sensitivefileConfiguration.EXCHANGE_Sensi, sensitivefileConfiguration.ROUTING_KEY, gson.toJson(domainx));
            rabbitTemplate.convertAndSend(TextFilesAccountsConfiguration.EXCHANGE_Text, TextFilesAccountsConfiguration.ROUTING_KEY, gson.toJson(domainx));
            for(UrlOb uri : list_url){
                uri.setUsername(targetModel.getUsername());
                uri.setIdTarget(targetModel.getIdTarget());
                rabbitTemplate.convertAndSend(SQLiRabbitConfiguration.EXCHANGE_SQLi, SQLiRabbitConfiguration.ROUTING_KEY, gson.toJson(uri));
                rabbitTemplate.convertAndSend(AdministrativePortalsConfiguration.EXCHANGE_Admin, AdministrativePortalsConfiguration.ROUTING_KEY, gson.toJson(uri));
                rabbitTemplate.convertAndSend(ClearTextHTTPConfiguration.EXCHANGE_Clear, ClearTextHTTPConfiguration.ROUTING_KEY, gson.toJson(uri));
                rabbitTemplate.convertAndSend(SessionIDinURLConfiguration.EXCHANGE_Ss, SessionIDinURLConfiguration.ROUTING_KEY, gson.toJson(uri));
                rabbitTemplate.convertAndSend(Base64EncodeSecretConfiguration.EXCHANGE_Base64, Base64EncodeSecretConfiguration.ROUTING_KEY, gson.toJson(uri));
                rabbitTemplate.convertAndSend(CrossSiteTracingConfiguration.EXCHANGE_XST, CrossSiteTracingConfiguration.ROUTING_KEY, gson.toJson(uri));
                rabbitTemplate.convertAndSend(DirectoryTraversalFilesRabbitConfiguration.EXCHANGE_DTF, DirectoryTraversalFilesRabbitConfiguration.ROUTING_KEY, gson.toJson(uri));
                rabbitTemplate.convertAndSend(HostHeaderAttackConfiguration.EXCHANGE_Host, HostHeaderAttackConfiguration.ROUTING_KEY, gson.toJson(uri));
                rabbitTemplate.convertAndSend(InsecureDeserializationConfiguration.EXCHANGE_ID, InsecureDeserializationConfiguration.ROUTING_KEY, gson.toJson(uri));
                rabbitTemplate.convertAndSend(InsecureLoginFormsConfiguration.EXCHANGE_IL, InsecureLoginFormsConfiguration.ROUTING_KEY, gson.toJson(uri));
                rabbitTemplate.convertAndSend(PHPCGIRCEConfiguration.EXCHANGE_PHPrce, PHPCGIRCEConfiguration.ROUTING_KEY, gson.toJson(uri));
                rabbitTemplate.convertAndSend(CMDInjectionConfiguration.EXCHANGE_CMDi, CMDInjectionConfiguration.ROUTING_KEY, gson.toJson(uri));
                rabbitTemplate.convertAndSend(HMTLInjectionConfiguration.EXCHANGE_HTMLi, HMTLInjectionConfiguration.ROUTING_KEY, gson.toJson(uri));
                rabbitTemplate.convertAndSend(IFrameInjectionConfiguration.EXCHANGE_IFi, IFrameInjectionConfiguration.ROUTING_KEY, gson.toJson(uri));
                rabbitTemplate.convertAndSend(PHPinjectionConfiguration.EXCHANGE_PHPi, PHPinjectionConfiguration.ROUTING_KEY, gson.toJson(uri));
                rabbitTemplate.convertAndSend(SSIinjectionConfiguration.EXCHANGE_SSIi, SSIinjectionConfiguration.ROUTING_KEY, gson.toJson(uri));
                rabbitTemplate.convertAndSend(XMLinectionConfiguration.EXCHANGE_XMLi, XMLinectionConfiguration.ROUTING_KEY, gson.toJson(uri));
                rabbitTemplate.convertAndSend(XSSConfiguration.EXCHANGE_XSS, XSSConfiguration.ROUTING_KEY, gson.toJson(uri));
                totalTask+=18;
            }

            Response response;
            do{
                Request request = new Request.Builder()
                        .url("http://localhost:26969/api/rabbit/update-job?id="+targetModel.getIdTarget()+"&total="+totalTask)
                        .method("GET", null)
                        .addHeader("Content-Type", "application/json")
                        .build();
                response = client.newCall(request).execute();
            }while (!response.isSuccessful());
            String output =response.body().string();
            System.err.println("UDPATE TOTAL TASK: "+ output + ":"+targetModel.getIdTarget()+"&total="+totalTask);
            //to do total tash

        } catch (ImmediateRequeueAmqpException e) {
            e.printStackTrace();
            throw new ImmediateRequeueAmqpException(e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AmqpRejectAndDontRequeueException("Error Handler converted exception to fatal", e);
        }
    }
}
