package com.zxk.springdataelasticsearch;

import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.IOException;

@SpringBootTest
class SpringDataElasticsearchApplicationTests {

	@Autowired
	private RestHighLevelClient client;

	public static String INDEX_TEST= null;
	public static String TYPE_TEST = null;


	static {
		//索引名称
		INDEX_TEST = "hello-index";
		//ElasticSearch 7.x以上，已经默认type为“_doc"
		TYPE_TEST = "_doc";
	}

	@Test
	void contextLoads() {
	}

	public boolean existsIndex(String index) throws IOException {
		org.elasticsearch.action.admin.indices.get.GetIndexRequest request = new GetIndexRequest();
		request.indices(index);
		boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
		System.out.println("existsIndex: " + exists);
		return exists;
	}

	@Test
	public void testIndex() throws Exception{
		if (!existsIndex(INDEX_TEST)){
			System.out.println("不存在索引");
		}else {
			System.out.println("存在索引");
		}
	}
}
