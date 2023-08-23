package com.wyhcode.controller;

import com.wyhcode.bean.AjaxResult;
import com.wyhcode.bean.es.Product;
import com.wyhcode.bean.vo.ProductRequestVO;
import com.wyhcode.service.product.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author weiyuhui
 * @date 2023/7/27 13:31
 * @description
 */

@RestController
@Api(tags = "产品")
public class ProductController {

    @Resource
    private ProductService productService;

    @PostMapping("/product")
    @ApiOperation("新增产品")
    public AjaxResult saveProduct(@RequestBody Product product){
        return AjaxResult.success(productService.saveProduct(product));
    }

    @DeleteMapping("/product/{id}")
    @ApiOperation("删除产品")
    public AjaxResult deleteProductById(@PathVariable("id") Long id){
        productService.delete(id);
        return AjaxResult.success("删除成功");
    }

    @GetMapping("/product/{id}")
    @ApiOperation("根据id查询产品")
    public AjaxResult getProductById(@PathVariable("id")Long id){
        return AjaxResult.success(productService.selectProductById(id));
    }

    @GetMapping("/allProduct")
    @ApiOperation("查询所有产品")
    public AjaxResult selectAllProduct(){
        return AjaxResult.success(productService.findAll());
    }

    @PostMapping("/productBatch")
    @ApiOperation("批量插入")
    public AjaxResult saveBatch(@RequestBody List<Product> products)
    {
        productService.saveBatch(products);
        return AjaxResult.success();
    }

    @GetMapping("/getPage")
    @ApiOperation("分页查询")
    public AjaxResult selectPage(@RequestParam("curPage") Integer curPage,
                                 @RequestParam("pageSize") Integer pageSize){
        return AjaxResult.success(productService.selectPage(curPage,pageSize,null));
    }

    @GetMapping("highLightPage")
    @ApiOperation("高亮查询")
    public AjaxResult highLightPage(ProductRequestVO productRequestVO){
        return AjaxResult.success(productService.searchHits(productRequestVO));
    }

}
