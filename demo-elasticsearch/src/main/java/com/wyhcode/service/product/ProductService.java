package com.wyhcode.service.product;

import com.wyhcode.bean.es.Product;
import com.wyhcode.bean.vo.ProductRequestVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;

/**
 * @author weiyuhui
 * @date 2023/7/27 13:34
 * @description
 */

public interface ProductService {

    /**
     * 新增产品
     * @param product
     * @return
     */
    Product saveProduct(Product product);

    /**
     * 根据id查询产品
     * @param id
     * @return
     */
    Product selectProductById(Long id);

    /**
     * 查询全部
     * @return
     */
    Iterable<Product> findAll();

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 批量插入
     * @param list
     */
    void saveBatch(List<Product> list);

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param sort
     * @return
     */
    Page<Product> selectPage(Integer currentPage, Integer pageSize, Sort sort);

    /**
     * 高亮分页查询
     * @param productRequestVO
     * @return
     */
    SearchHits<Product> searchHits(ProductRequestVO productRequestVO);
}
