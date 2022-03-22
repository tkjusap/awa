package com.funtap.awass.Model.request;

import lombok.Data;

@Data
public class TargetScanModel {
    private long id;
    private String username;
    private String title;
    private String protocal;
    private String url;
    private int status;
    private String linklogin;
    private String uname;
    private String passwd;
    private String cookie;
}
