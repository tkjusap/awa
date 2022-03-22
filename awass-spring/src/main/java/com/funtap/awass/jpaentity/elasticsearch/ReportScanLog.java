package com.funtap.awass.jpaentity.elasticsearch;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Document(indexName = "reportscan")
public class ReportScanLog {
    @Id
    private String id;
    private long idTarget;
    private String url;
    private String result;
    private String level;
    private String username;
    private String des;
    private String updatedTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());


    private Long createdDate = new Date().getTime(); // ngày tạo
}
