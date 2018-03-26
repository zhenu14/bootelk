package com.bootelk.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.bootelk.web.service.MailService;
import com.bootelk.web.util.ElasticsearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @RequestMapping(value = {"send"})
    public Object send() {
        List<Map<String, Object>> results = ElasticsearchUtils.RangeSearch("@timestamp","now-24h/h","now/h","");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{" + results.size() + "}条数据");
        stringBuilder.append("\r\n");
        for(Map<String, Object> item : results){
            System.out.println(JSONObject.toJSONString(item));
            stringBuilder.append(JSONObject.toJSONString(item));
            stringBuilder.append("\r\n");
        }
        mailService.sendSimpleMail("790507071@qq.com", "主题：异常日志", stringBuilder.toString());
        return "success";
    }
}
