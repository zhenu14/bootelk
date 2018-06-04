package com.logalert.core.job;

import com.alibaba.fastjson.JSONObject;
import com.logalert.core.entity.EmailConfig;
import com.logalert.core.repository.EmailConfigRepository;
import com.logalert.core.service.MailService;
import com.logalert.util.ElasticSearchUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EmailJobs {

    private Logger logger = LoggerFactory.getLogger(EmailJobs.class);

    public final static long ONEHOUR =  60 * 1000 * 60;  //60分钟

    public final static long five_minute =  60 * 1000 * 5;  //60分钟

    private final MailService mailService;

    @Autowired
    private EmailConfigRepository emailConfigRepository;

    @Autowired
    public EmailJobs(MailService mailService) {
        this.mailService = mailService;
    }

//    @Scheduled(fixedRate=ONEHOUR)
    public void sendMail() {
        String field = "appname,message,ip,logtime";

//        StringBuilder stringBuilder = new StringBuilder();

        Map<String,Long> appnames = ElasticSearchUtils.getAppnames("@timestamp","now-1h/h","now/h");
        if(appnames == null){
            logger.info("job return null");
            return;
        }

        List<Map<String, Object>> results;

        Map<String,StringBuilder> context = new HashMap<>();
        for (String key : appnames.keySet()) {
            context.put(key,new StringBuilder("共查询到" + key + "{" + appnames.get(key) + "}条数据 \n"));
            results = ElasticSearchUtils.getRangeByAppname("@timestamp","now-1h/h","now/h",field,key);
            for(Map<String, Object> item : results){
                if(item.get("appname") == key || item.get("appname").equals(key) ){
                    context.get(key).append(JSONObject.toJSONString(item));
                    context.get(key).append("\r\n");
                }
            }
        }
        List<EmailConfig> list = emailConfigRepository.findEmailReceiverByAppname(appnames.keySet());
        for(EmailConfig e : list){
            mailService.sendSimpleMail(e.getEmailreceiver(), "主题：日志异常", context.get(e.getAppname()).toString());
        }
    }

//    @Scheduled(fixedRate=five_minute)
    public void log() {
        for(int i=0;i<5;i++) {
            logger.info("输出info  ");
            logger.warn("输出warn");
            logger.error("输出error");
        }
    }
}
