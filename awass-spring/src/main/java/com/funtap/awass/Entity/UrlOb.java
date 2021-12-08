package com.funtap.awass.Entity;

public class UrlOb {
    private String url;
    private String method;
    private String param;
    private int depth;
    private boolean scan;
    public UrlOb() {
    }

    public UrlOb(String url, String method, String param, int depth, boolean scan) {
        this.url = url;
        this.method = method;
        this.param = param;
        this.depth = depth;
        this.scan = scan;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public boolean isScan() {
        return scan;
    }

    public void setScan(boolean scan) {
        this.scan = scan;
    }

    @Override
    public String toString() {
        return "UrlOb{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", param='" + param + '\'' +
                ", depth=" + depth +
                ", scan=" + scan +
                '}';
    }
}
