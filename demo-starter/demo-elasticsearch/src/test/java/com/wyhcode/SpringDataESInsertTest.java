package com.wyhcode;

import com.wyhcode.bean.es.Product;
import com.wyhcode.respsitory.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weiyuhui
 * @date 2023/7/26 17:39
 * @description
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticSearchApplication.class)
@Slf4j
public class SpringDataESInsertTest {

    @Resource
    private ElasticsearchRepository elasticsearchRepository;

    @Resource
    private ProductMapper productMapper;
    @Test
    public void createIndex(){
        // 系统初始化会自动创建索引
        log.info("创建索引");
    }

    @Test
    public void insert(){
        List<Product> list = new ArrayList<>();
        for (int i = 10;i <= 100;i++){
            Product product = new Product();
            product.setId((long) i);
            product.setTitle("Iphone " + i);
            product.setPrice(9999.0);
            product.setCategory("苹果");
            product.setImages("https://sada.sadad.asd/index.jpg");
            list.add(product);
        }
        productMapper.saveAll(list);
    }
}
