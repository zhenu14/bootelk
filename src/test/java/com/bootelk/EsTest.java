package com.bootelk;

import com.alibaba.fastjson.JSONObject;
import com.bootelk.web.util.ElasticsearchUtils;
import com.bootelk.web.util.EsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
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
        ElasticsearchUtils.createIndex("abc");
    }

    @Test
    public void deleteIndexTest() {
        ElasticsearchUtils.deleteIndex("loginfo_log");
        ElasticsearchUtils.deleteIndex("logwarn_log");
        ElasticsearchUtils.deleteIndex("logerror_log");
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

            ElasticsearchUtils.addData(JSONObject.parseObject(JSONObject.toJSONString(map)), "abc", "log", String.valueOf(i));
        }
    }

    @Test
    public void searchDataByIdTest() {
        Map<String, Object> map = ElasticsearchUtils.searchDataById("abc", "about_test", "id=8", null);
        System.out.println(JSONObject.toJSONString(map));
    }

    @Test
    public void searchListData()throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        Date date1 = format.parse("2018-03-22 10:20:37,846");
        Date date2 = format.parse("2018-03-22 10:26:26,019");
        List<Map<String, Object>> list = ElasticsearchUtils.searchListData("abc", "logs",date1.getTime(),date2.getTime(), 0, "", "", false, "", "");
        for (Map<String, Object> item : list) {
            System.out.println(JSONObject.toJSONString(item));
        }
    }

    /**
     * 使用分词查询,并分页
     * <p>
     * index          索引名称
     * type           类型名称,可传入多个type逗号分隔
     * currentPage    当前页
     * pageSize       每页显示条数
     * startTime      开始时间
     * endTime        结束时间
     * fields         需要显示的字段，逗号分隔（缺省为全部字段）
     * sortField      排序字段
     * matchPhrase    true 使用，短语精准匹配
     * highlightField 高亮字段
     * matchStr       过滤条件（xxx=111,aaa=222）
     */
    @Test
    public void searchDataPage() {

        EsPage esPage = ElasticsearchUtils.searchDataPage("abc", "logs", 50, 10, 0, 0, "", "", false, "", "");

        for (Map<String, Object> item : esPage.getRecordList()) {
            System.out.println(JSONObject.toJSONString(item));
        }
    }
}
