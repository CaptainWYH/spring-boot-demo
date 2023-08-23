package com.wyhcode.respsitory;

import com.wyhcode.bean.es.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author weiyuhui
 * @date 2023/7/26 17:49
 * @description
 */

@Repository
public interface ProductMapper extends ElasticsearchRepository<Product,Long> {
}
