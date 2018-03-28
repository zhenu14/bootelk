//package com.bootelk.web.conf;
//
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import java.net.InetAddress;
//
//@Configuration
//public class ElasticsearchConfiguration {
//    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchConfiguration.class);
//
//    /**
//     * elk集群地址
//     */
//    @Value("${elasticsearch.ip}")
//    private String hostName;
//    /**
//     * 端口
//     */
//    @Value("${elasticsearch.port}")
//    private String port;
//    /**
//     * 集群名称
//     */
//    @Value("${elasticsearch.cluster.name}")
//    private String clusterName;
//
//    /**
//     * 连接池
//     */
//    @Value("${elasticsearch.pool}")
//    private String poolSize;
//
//    @Bean
//    public TransportClient init() {
//
//        TransportClient transportClient = null;
//
//        try {
//            // 配置信息
//            Settings esSetting = Settings.builder()
//                    .put("cluster.name", clusterName)
////                    .put("xpack.security.user", "elastic:123456")
//                    .put("client.transport.sniff", true)//增加嗅探机制，找到ES集群
//                    .put("thread_pool.search.size", Integer.parseInt(poolSize))//增加线程池个数，暂时设为5
//                    .build();
//
//            transportClient = new PreBuiltTransportClient(esSetting);
//            InetSocketTransportAddress inetSocketTransportAddress = new InetSocketTransportAddress(InetAddress.getByName(hostName), Integer.valueOf(port));
//            transportClient.addTransportAddresses(inetSocketTransportAddress);
//
//        } catch (Exception e) {
//            logger.error("elasticsearch TransportClient create error!!!", e);
//        }
//
//        return transportClient;
//    }
//}
