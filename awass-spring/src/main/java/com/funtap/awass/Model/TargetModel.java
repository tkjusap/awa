package com.funtap.awass.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class TargetModel {
    private long idTarget;
    private String url;
    private String cookie;
    private String linklogin;
    private String uname;
    private String passwd;
    private String username;
}
