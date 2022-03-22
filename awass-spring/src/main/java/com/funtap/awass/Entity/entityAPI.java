package com.funtap.awass.Entity;

import java.util.List;

public class entityAPI {

    private String url;
    private String domain;
    private String folder;
    private String param;

    public entityAPI() {
    }

    public entityAPI(String url, String domain, String folder, String param) {
        this.url = url;
        this.domain = domain;
        this.folder = folder;
        this.param = param;
    }

    @Override
    public String toString() {
        return "entityAPI{" +
                "url='" + url + '\'' +
                ", domain='" + domain + '\'' +
                ", folder='" + folder + '\'' +
                ", param='" + param + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
