package com.bootelk;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RestClientTest {
    private static RestClient restClient;
    @Before
    public void getRestClient(){
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,new UsernamePasswordCredentials("elastic", "123456"));
        restClient = RestClient.builder(new HttpHost("localhost",9200,"http"))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                }).build();

    }

//    @Before
    public void getRest(){
        restClient = RestClient.builder(new HttpHost("localhost", 9200, "http")).build();
    }

    /**
     * 查看api信息
     * @throws Exception
     */
    @Test
    public void CatApi() throws Exception{
        String method = "GET";
        String endpoint = "/_cat";
        Response response = restClient.performRequest(method,endpoint);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 创建索引
     * @throws Exception
     */
    @Test
    public void CreateIndex() throws Exception{
        String method = "PUT";
        String endpoint = "/test-index";
        Response response = restClient.performRequest(method,endpoint);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 创建文档
     * @throws Exception
     */
    @Test
    public void CreateDocument()throws Exception{

        String method = "PUT";
        String endpoint = "/test-index/test/1";
        HttpEntity entity = new NStringEntity(
                "{\n" +
                        "    \"user\" : \"kimchy\",\n" +
                        "    \"post_date\" : \"2018-03-22T14:12:12\",\n" +
                        "    \"message\" : \"trying out Elasticsearch\"\n" +
                        "}", ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest(method,endpoint, Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 获取文档
     * @throws Exception
     */
    @Test
    public void getDocument()throws Exception{
        String method = "GET";
        String endpoint = "/test-index/test/1";
        Response response = restClient.performRequest(method,endpoint);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 查询所有数据
     * @throws Exception
     */
    @Test
    public void QueryAll() throws Exception {
        String method = "POST";
        String endpoint = "/test-index/test/_search";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"query\": {\n" +
                "    \"match_all\": {}\n" +
                "  }\n" +
                "}", ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 根据ID获取
     * @throws Exception
     */
    @Test
    public void QueryByField() throws Exception {
        String method = "POST";
        String endpoint = "/test-index/test/_search";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"query\": {\n" +
                "    \"match\": {\n" +
                "      \"id\": \"1\"\n" +
                "    }\n" +
                "  }\n" +
                "}", ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 更新数据
     * @throws Exception
     */
    @Test
    public void UpdateByScript() throws Exception {
        String method = "POST";
        String endpoint = "/test-index/test/1/_update";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"doc\": {\n" +
                "    \"user\":\"大美女\"\n" +
                "   }\n" +
                "}", ContentType.APPLICATION_JSON);
        Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    @Test
    public void aa() throws Exception{
        String method = "GET";
        String endpoint = "/abc-error-*/_search";
        HttpEntity entity = new NStringEntity("" +
                "{\n" +
                "  \"query\": {\n" +
                "    \"range\": {\n" +
                "      \"@timestamp\": {\n" +
                "        \"from\" : \"now-24h/h\",\n" +
                "        \"to\" : \"now/h\"\n" +
                "       }\n" +
                "     }\n" +
                "   }\n" +
                "}", ContentType.APPLICATION_JSON);
        Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}
