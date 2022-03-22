package com.funtap.awass.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class UrlOb {
    private String url;
    private String method;
    private String param;
    private int depth;
    private boolean scan;
    private long idTarget;
    private String cookie;
    private String username;

    @Override
    public String toString() {
        return "UrlOb{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", param='" + param + '\'' +
                ", depth=" + depth +
                ", scan=" + scan +
                ", idTarget=" + idTarget +
                ", cookie='" + cookie + '\'' +
                '}';
    }
}
