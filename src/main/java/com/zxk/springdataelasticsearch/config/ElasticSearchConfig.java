package com.zxk.springdataelasticsearch.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

/**
 * @program: spring-data-elasticsearch
 * @description: ElasticSearch 配置
 * @author: Zhangxike
 * @create: 2020-09-15 10:56
 **/
@Configuration
@Slf4j
public class ElasticSearchConfig {
    // 集群地址，多个用,隔开
    private static String hosts = "127.0.0.1";
    // 使用的端口号
    private static int[] port = new int[]{9201,9202,9203};

    private static String schema = "http";

    private static ArrayList<HttpHost> hostList = null;

    /**
     * 连接超时时间
     */
    private static int connectTimeOut = 1000;

    /**
     * socket连接超时时间
     */
    private static int socketTimeOut = 3000;

    /**
     * 获取连接的超时时间
     */
    private static int connectionRequestTimeOit = 500;

    /**
     * 最大连接数
     */
    private static int maxConnectNum  = 100;

    /**
     * 最大路由连接数
     */
    private static int maxConnectPerRoute = 100;

    static {
        hostList = new ArrayList<>();
        String[] hostStrs = hosts.split(",");
        for (int i : port) {
            hostList.add(new HttpHost(hostStrs[0], i, schema));
        }
    }


    @Bean
    public RestHighLevelClient client(){
        RestClientBuilder builder = RestClient.builder(hostList.toArray(new HttpHost[0]));
        //异步httpClient连接延迟配置
        builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder builder) {
                builder.setConnectTimeout(connectTimeOut);
                builder.setSocketTimeout(socketTimeOut);
                builder.setConnectionRequestTimeout(connectionRequestTimeOit);
                return builder;
            }
        });
        //异步httpClient连接数配置
        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                httpAsyncClientBuilder.setMaxConnTotal(maxConnectNum);
                httpAsyncClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
                return httpAsyncClientBuilder;
            }
        });

        //设置监听器，每次节点失败都可以监听到，可以做额外处理
        builder.setFailureListener(new RestClient.FailureListener(){
            @Override
            public void onFailure(Node node) {
                super.onFailure(node);
                log.error(node.getName()+node.getHost()+"--------->该节点失败");
            }
        });
        return new RestHighLevelClient(builder);
    }
}
