package com.funtap.awass.jpaentity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "report")
@Table(name = "report")
@EntityListeners(AuditingEntityListener.class)
public class ReportScan extends BaseEntity{
    private long idTarget;
    private String url;
    private String result;
    private String level;
    private String username;
    private String des;
}
