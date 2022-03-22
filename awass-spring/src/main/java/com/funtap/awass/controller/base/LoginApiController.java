package com.funtap.awass.controller.base;

import com.funtap.awass.Entity.UrlOb;
import com.funtap.awass.Model.request.LoginModel;
import com.funtap.awass.Model.response.ApiResponse;
import com.funtap.awass.jpaentity.ScanTarget;
import com.funtap.awass.jpaentity.elasticsearch.ReportScanLog;
import com.funtap.awass.rabbitmq.configuration.DemoRabbitConfiguration;
import com.funtap.awass.rabbitmq.configuration.SQLiRabbitConfiguration;
import com.funtap.awass.repository.elasticsearch.ReportScanLogRepository;
import com.funtap.awass.service.LoginService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping()
@CrossOrigin("*")
public class LoginApiController {
    private static final Logger logger = Logger.getLogger(LoginApiController.class);
    @Autowired
    LoginService loginService;
    @Autowired
    Gson gson;
    @Autowired
    ReportScanLogRepository reportScanLogRepository;
    @PostMapping("/login")
    public synchronized ApiResponse login(@RequestBody LoginModel loginModel, HttpServletRequest httpReq) {
        try {
            return loginService.loginService(loginModel, httpReq);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ApiResponse.fail("ERROR");
        }
    }


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("test")
    public String test(String test) {
        rabbitTemplate.convertAndSend(DemoRabbitConfiguration.EXCHANGE_HELLO, DemoRabbitConfiguration.ROUTING_KEY, test);
        return "OK" + test;
    }

    @GetMapping("test2")
    public String test2() {
        UrlOb uri = new UrlOb();
        uri.setUrl("https://henhoketban.vn/profile.php");
        uri.setMethod("get");
        uri.setScan(false);
        uri.setDepth(1);
        uri.setParam("id=118549");

        rabbitTemplate.convertAndSend(SQLiRabbitConfiguration.EXCHANGE_SQLi, SQLiRabbitConfiguration.ROUTING_KEY, gson.toJson(uri));
        return "OK";
    }

    @PostMapping("update-vul")
    public String saveVul(@RequestBody ReportScanLog reportScan) {
        try {
            reportScanLogRepository.save(reportScan);
            return "Update ok";
        }catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
