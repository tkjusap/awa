package com.funtap.awass.jpaentity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "user")
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity{
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    public static final int ENABLE_DEATIVE = 0;
    public static final int ENABLE_ACTIVE = 1;

    @Column(insertable = true, updatable = true, nullable = false, unique = true)
    private String username;
    @JsonIgnore
    private String password;
    @JsonIgnore
    @Column(insertable = false, updatable = true, nullable = false, columnDefinition = "varchar(45) default 'ROLE_USER'")
    private String role;
    private String phone;
    @Column(insertable = false, updatable = true, columnDefinition = "int(1) default 0")
    private int enable =0;
}
