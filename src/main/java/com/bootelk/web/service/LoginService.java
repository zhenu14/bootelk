package com.bootelk.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private Logger logger = LoggerFactory.getLogger(getClass().getName());
    public void login() {
        logger.info("进入Service方法，执行登录查询");
    }
}
