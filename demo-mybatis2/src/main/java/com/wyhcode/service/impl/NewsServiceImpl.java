package com.wyhcode.service.impl;
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
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    @Resource
    private NewsMapper newsMapper;

    @Override
    public PageInfo<NewsResponseVO> getAddressPage(int page, int size) {
        // 开启分页
        PageHelper.startPage(page,size);
        System.out.println("1");
        List<News> newsPage = newsMapper.getNewsPage();
        System.out.println("1222");
        List<NewsResponseVO> collect = newsPage.stream().map(e -> {
            NewsResponseVO newsResponseVO = new NewsResponseVO();
            BeanUtils.copyProperties(e, newsResponseVO);
            return newsResponseVO;
        }).collect(Collectors.toList());

        // 第一原则   尽量少的次数查询数据库
        Map<Long, News> newsMap = newsPage.stream().collect(Collectors.toMap(News::getId, item -> item));
        Map<Long, String> collect1 = newsPage.stream().collect(Collectors.toMap(News::getId, News::getContent));

        // group By 分组


        PageInfo<NewsResponseVO> result = new PageInfo<>(collect);
        return result;
    }
}
