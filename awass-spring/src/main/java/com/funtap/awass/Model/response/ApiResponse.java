package com.funtap.awass.Model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -7823237036077824442L;
    public int errorCode;
    public int totalRecord=0;
    public Object msg;

    public static ApiResponse success(Object msg) {
        ApiResponse response = new ApiResponse();
        response.setErrorCode(0);
        response.setMsg(msg);
        return response;
    }

    public static ApiResponse success(Object msg, int totalRecord) {
        ApiResponse response = new ApiResponse();
        response.setTotalRecord(totalRecord);
        response.setErrorCode(0);
        response.setMsg(msg);
        return response;
    }

    public static ApiResponse success() {
        ApiResponse response = new ApiResponse();
        response.setErrorCode(0);
        return response;
    }

    public static ApiResponse fail(String body) {
        ApiResponse response = new ApiResponse();
        response.setErrorCode(1);
        response.setMsg(body);
        return response;
    }

    public static ApiResponse fail() {
        ApiResponse response = new ApiResponse();
        response.setErrorCode(1);
        response.setMsg("ERROR");
        return response;
    }
}
