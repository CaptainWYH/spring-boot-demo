package com.wyhcode.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyhcode.mapper.NewsMapper;
import com.wyhcode.pojo.News;
import com.wyhcode.pojo.vo.NewsResponseVO;
import com.wyhcode.service.NewsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    @Resource
    private NewsMapper newsMapper;
    @Override
    public PageInfo<NewsResponseVO> getAddressPage(int page, int size) {
        // 开启分页
        PageHelper.startPage(page,size);
        System.out.println("2");
        List<News> newsPage = newsMapper.getNewsPage();
        List<NewsResponseVO> collect = newsPage.stream().map(e -> {
            NewsResponseVO newsResponseVO = new NewsResponseVO();
            BeanUtils.copyProperties(e, newsResponseVO);
            return newsResponseVO;
        }).collect(Collectors.toList());
        PageInfo<NewsResponseVO> result = new PageInfo<>(collect);
        return result;
    }
}
