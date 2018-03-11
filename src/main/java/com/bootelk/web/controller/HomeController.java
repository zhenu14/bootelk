package com.bootelk.web.controller;

import com.bootelk.web.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    LoginService loginService;

    @RequestMapping(value = {""})
    public String main(){
        logger.debug("home/index");
        logger.info("home/index");
        return "home/index";
    }

    @RequestMapping(value = {"index"})
    public Object index() {
        for(int i = 0;i < 20;i++){
            logger.info("------------啊info-----------");
            logger.warn("------------啊warn-----------");
            logger.error("------------啊error-----------");
        }
        return "success";
    }

    @RequestMapping(value = {"login"})
    public Object login() {
        logger.info("进入了login方法");

        loginService.login();

        logger.info("登录成功");

        return "success";
    }
}
