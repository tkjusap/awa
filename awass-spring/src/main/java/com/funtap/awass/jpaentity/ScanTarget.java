package com.funtap.awass.jpaentity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "scantarget")
@Table(name = "scantarget")
@EntityListeners(AuditingEntityListener.class)
public class ScanTarget extends BaseEntity{
    private String username;
    private String title;
    private String protocal;
    private String url;
    private int status;
    private int totaljob =0;//tong cac job
    private int job = 0;//cac job da hoan thanh
    private String linklogin;
    private String uname;
    private String passwd;
    private String cookie;
}
