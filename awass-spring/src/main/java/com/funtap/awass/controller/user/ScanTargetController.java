package com.funtap.awass.controller.user;

import com.funtap.awass.Model.TargetModel;
import com.funtap.awass.Model.request.TargetScanModel;
import com.funtap.awass.Model.request.UserCreateDto;
import com.funtap.awass.Model.response.ApiResponse;
import com.funtap.awass.Model.response.CriticalModel;
import com.funtap.awass.Model.response.Domainx;
import com.funtap.awass.Model.response.RoleModel;
import com.funtap.awass.jpaentity.ScanTarget;
import com.funtap.awass.jpaentity.User;
import com.funtap.awass.jpaentity.elasticsearch.ReportScanLog;
import com.funtap.awass.rabbitmq.configuration.DemoRabbitConfiguration;
import com.funtap.awass.repository.ReportScanRepository;
import com.funtap.awass.repository.ScanTargetRepository;
import com.funtap.awass.repository.UserRepository;
import com.funtap.awass.repository.elasticsearch.ReportScanLogRepository;
import com.funtap.awass.security.JwtService;
import com.funtap.awass.service.LoginService;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("user")
@CrossOrigin("*")
public class ScanTargetController {
    @Autowired
    LoginService loginService;
    @Autowired
    JwtService jwtService;
    @Autowired
    ScanTargetRepository scanTargetRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReportScanLogRepository reportScanLogRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    Gson gson;
    @PostMapping("create-target")
    public ApiResponse createTarget(@RequestBody TargetScanModel targetScanModel) {
        try {
            User user = jwtService.getUser();
            if(user == null) {
                throw new Exception("Error");
            }
            ScanTarget scanTarget = new ScanTarget();
            scanTarget.setUsername(user.getUsername());
            scanTarget.setTitle(targetScanModel.getTitle());
            scanTarget.setUrl(targetScanModel.getUrl());
            scanTarget.setProtocal(targetScanModel.getProtocal());
            scanTarget.setLinklogin(targetScanModel.getLinklogin());
            scanTarget.setUname(targetScanModel.getUname());
            scanTarget.setPasswd(targetScanModel.getPasswd());
            scanTarget.setCookie(targetScanModel.getCookie());
            targetScanModel.setProtocal(targetScanModel.getProtocal());
            scanTargetRepository.save(scanTarget);
            TargetModel targetModel = TargetModel.of(scanTarget.getId(), targetScanModel.getUrl(), targetScanModel.getCookie(), targetScanModel.getLinklogin(), targetScanModel.getUname(), targetScanModel.getPasswd(),user.getUsername());
            rabbitTemplate.convertAndSend(DemoRabbitConfiguration.EXCHANGE_HELLO, DemoRabbitConfiguration.ROUTING_KEY, gson.toJson(targetModel));
            return ApiResponse.success(scanTarget);
        }catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }

    @GetMapping("get-critical")
    public ApiResponse getCritical() {
        try {
            Iterable<ReportScanLog> listReport = null;
            User user = jwtService.getUser();
            if(user.getRole().equals(User.ROLE_ADMIN)) {
                listReport = reportScanLogRepository.findAll();
            }else {
                listReport = reportScanLogRepository.findByUsername(user.getUsername());
            }
            //td do
            CriticalModel criticalModel = new CriticalModel();
            listReport.forEach((data) -> {
                if(data.getLevel().equals("HIGH")) {
                    criticalModel.setHigh(criticalModel.getHigh()+1);
                }else if(data.getLevel().equals("CRITICAL")) {
                    criticalModel.setCritical(criticalModel.getCritical()+1);
                }else if(data.getLevel().equals("MEDIUM")) {
                    criticalModel.setMedium(criticalModel.getMedium()+1);
                }else if(data.getLevel().equals("LOW")) {
                    criticalModel.setLow(criticalModel.getLow()+1);
                }

            });
            return ApiResponse.success(criticalModel);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ApiResponse.fail("Co loi xay ra");
    }

    @GetMapping("get-role")
    public ApiResponse getRole() {
        try {
            RoleModel ru = new RoleModel();
            User user = jwtService.getUser();
            ru.setUsername(user.getUsername());
            ru.setRole(user.getRole());
            return ApiResponse.success(ru);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ApiResponse.fail("Co loi xay ra");
    }

    @GetMapping("get-domain")
    public ApiResponse getDomainx(int id) {
        try {
            Domainx dom = new Domainx();
            dom.setId(id);
            return ApiResponse.success(dom);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ApiResponse.fail("Co loi xay ra");
    }

    @GetMapping("scan-target")
    public ApiResponse getTarget(Pageable pageable) {
        try {
            User user = jwtService.getUser();
            if(user == null) {
                throw new Exception("Error");
            }
            if(user.getRole().equals(User.ROLE_ADMIN)) {
                return ApiResponse.success(scanTargetRepository.findAll(pageable));
            }else {
                return ApiResponse.success(scanTargetRepository.findByUsername(user.getUsername(),pageable));
            }
        }catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }

    @GetMapping("all")
    public ApiResponse getUser(Pageable pageable) {
        try {
            return ApiResponse.success(userRepository.findAll(pageable));
        }catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }

    @GetMapping("get-vul")
    public ApiResponse getVul(String idTarget) {
        try {
            User user = jwtService.getUser();
            if(user == null) {
                throw new Exception("Error");
            }
            return ApiResponse.success(reportScanLogRepository.findByIdTarget(idTarget));
        }catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }
    @GetMapping("check-vul")
    public ApiResponse checkVul(long idTarget) {
        try {
            User user = jwtService.getUser();
            if(user == null) {
                throw new Exception("Error");
            }
            return ApiResponse.success(scanTargetRepository.findById(idTarget).get());
        }catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }


    @PostMapping("/create-user")
    public synchronized ApiResponse registerByUser(@RequestBody UserCreateDto userCreateDto, HttpServletRequest httpReq) {
        try {
            return loginService.registerByUser(userCreateDto, httpReq);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.fail(e.getMessage());
        }
    }
}
