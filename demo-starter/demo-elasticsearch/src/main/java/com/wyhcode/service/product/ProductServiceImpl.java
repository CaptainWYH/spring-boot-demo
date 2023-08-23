package com.wyhcode.service.product;

import cn.hutool.db.PageResult;
import com.wyhcode.bean.es.Product;
import com.wyhcode.bean.vo.ProductRequestVO;
import com.wyhcode.respsitory.ProductMapper;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author weiyuhui
 * @date 2023/7/27 13:34
 * @description
 */

@Service
public class ProductServiceImpl implements ProductService{

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Resource
    private ProductMapper productMapper;




    @Override
    public Product saveProduct(Product product) {
        return productMapper.save(product);
    }

    @Override
    public Product selectProductById(Long id) {
        return productMapper.findById(id).get();
    }

    @Override
    public Iterable<Product> findAll() {
        return productMapper.findAll();
    }

    @Override
    public void delete(Long id) {
        productMapper.deleteById(id);
    }

    @Override
    public void saveBatch(List<Product> list) {
        productMapper.saveAll(list);
    }

    @Override
    public Page<Product> selectPage(Integer currentPage, Integer pageSize, Sort sort) {
        sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(currentPage, pageSize, sort);
        Page<Product> pageProduct = productMapper.findAll(pageRequest);
        return pageProduct;
    }

    @Override
    public SearchHits<Product> searchHits(ProductRequestVO productRequestVO) {
        PageRequest pageRequest = PageRequest.of(productRequestVO.getCurPage(), productRequestVO.getPageSize());
        // 构建查询条件
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("title", productRequestVO.getKeyword());
        // 设置高亮字体
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font color='red'>");
        highlightBuilder.postTags("</font>");
        // 设置高亮字段
        highlightBuilder.field("title");
        // 构建查询
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.withQuery(queryBuilder)
                .withHighlightBuilder(highlightBuilder)
                .withPageable(pageRequest)
                .build();
        return elasticsearchRestTemplate.search(searchQuery, Product.class);
    }
}
