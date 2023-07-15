package com.wyhcode.controller;


import com.github.pagehelper.PageInfo;
import com.wyhcode.pojo.vo.NewsResponseVO;
import com.wyhcode.pojo.vo.TestRequestVO;
import com.wyhcode.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/news")
@Api(tags = "新闻管理")
public class NewsController {

    @Resource
    private NewsService newsService;
    @GetMapping("/get")
    @ApiOperation(value = "获取新闻分页")
    public PageInfo<NewsResponseVO> getNewsPage(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "3")int pageSize){
        return newsService.getAddressPage(pageNum,pageSize);
    }


    @GetMapping("/get-page/{pageNum}/{pageSize}")
    @ApiOperation(value = "获取新闻分页2")
    public PageInfo<NewsResponseVO> getNewsPageRest(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize")int pageSize){
        return newsService.getAddressPage(pageNum,pageSize);
    }

    @GetMapping("/test")
    @ApiOperation(value = "这是一个测试接口")
    public NewsResponseVO test(TestRequestVO testRequestVO){
        NewsResponseVO newsResponseVO = new NewsResponseVO();
        newsResponseVO.setContent(testRequestVO.getName());
        return newsResponseVO;
    }
}
