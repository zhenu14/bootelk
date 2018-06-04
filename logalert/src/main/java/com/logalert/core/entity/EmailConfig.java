package com.logalert.core.entity;

import javax.persistence.*;

@Entity
@Table(name="emailconfig")
public class EmailConfig {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long  id;

    @Column(name="appname")
    private String appname;

    @Column(name="emailreceiver")
    private String emailreceiver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getEmailreceiver() {
        return emailreceiver;
    }

    public void setEmailreceiver(String emailreceiver) {
        this.emailreceiver = emailreceiver;
    }
}




