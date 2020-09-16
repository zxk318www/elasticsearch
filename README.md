# elasticsearch
spring-data+elasticsearch

###ElasticSearch：
    版本7.8.0，以单机多节点形式启动，端口为 9201,9202,9203，节点名称分别为node1,node2,node3
    索引库Document为 {
    "id":{"type":"long","store":true}
    ,"title":{"type":"text","analyzer":"ik_smart","store":true}
    ,"content":{"type":"text","analyzer":"ik_smart","store":true}
    }
###内容包含
    包含了 spring-data(使用RestHighLevelClient配置)，通过CURD方式对索引库进行增删改查
