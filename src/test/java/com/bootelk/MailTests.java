package com.bootelk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bootelk.web.entity.Log;
import com.bootelk.web.service.MailService;
import com.bootelk.web.util.ElasticsearchUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringbootApplication.class)
public class MailTests {
    @Autowired
    private MailService mailService;

    private String to = "790507071@qq.com";

    @Test
    public void sendSimpleMail() {
        String field = "appname,level,ip,message";
        List<Map<String, Object>> results = ElasticsearchUtils.RangeSearch("@timestamp","now-24h/h","now/h",field);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("共查询到{" + results.size() + "}条数据");
        stringBuilder.append("\r\n");

        for(Map<String, Object> item : results){
            stringBuilder.append(JSONObject.toJSONString(item));
            stringBuilder.append("\r\n");
        }
        mailService.sendSimpleMail("790507071@qq.com", "主题：异常日志", stringBuilder.toString());
    }

    @Test
    public void sendAttachmentsMail() {
        mailService.sendAttachmentsMail(to, "主题：带附件的邮件", "有附件，请查收！", "C:\\Users\\Xu\\Desktop\\csdn\\1.png");
    }
}
