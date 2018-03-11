package com.bootelk;

import com.alibaba.fastjson.JSONObject;
import com.bootelk.web.util.ElasticsearchUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringbootApplication.class)
public class EsTest {

    @Test
    public void createIndexTest() {
        ElasticsearchUtils.createIndex("demo_index");
    }

    @Test
    public void deleteIndexTest() {
        ElasticsearchUtils.deleteIndex("my_index");
    }

    @Test
    public void addDataTest() {

        for (int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("name", "zzb" + i);
            map.put("age", i);
            map.put("interests", new String[]{"阅读", "学习"});
            map.put("about", "世界上没有优秀的理念，只有脚踏实地的结果");
            map.put("processTime", new Date());

            ElasticsearchUtils.addData(JSONObject.parseObject(JSONObject.toJSONString(map)), "demo_index", "about_test", "id=" + i);
        }
    }

    @Test
    public void searchDataByIdTest() {
        Map<String, Object> map = ElasticsearchUtils.searchDataById("demo_index", "about_test", "id=8", null);
        System.out.println(JSONObject.toJSONString(map));
    }

    @Test
    public void searchListData() {
        List<Map<String, Object>> list = ElasticsearchUtils.searchListData("demo_index", "about_test", 	1520490740000l, 	1520490750000l, 0, "", "", false, "", "name=dl");
        for (Map<String, Object> item : list) {
            System.out.println(JSONObject.toJSONString(item));
        }
    }

    @Test
    public void aa(){
        java.util.Date dt = new Date();
        System.out.println(dt.toString());   //java.util.Date的含义
        long lSysTime1 = dt.getTime() / 1000;   //得到秒数，Date类型的getTime()返回毫秒数
        System.out.println(lSysTime1);
        long millSec = 1509959382607l;  //1520491842l

        SimpleDateFormat sdf = new SimpleDateFormat("h时m分s秒");

        Date date= new Date(millSec);

        System.out.println(sdf.format(date));
    }
}
