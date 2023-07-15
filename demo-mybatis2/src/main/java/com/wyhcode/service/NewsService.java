package com.wyhcode.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyhcode.pojo.Address;
import com.wyhcode.pojo.News;
import com.wyhcode.pojo.vo.NewsResponseVO;
import org.springframework.stereotype.Service;

public interface NewsService {

    PageInfo<NewsResponseVO> getAddressPage(int page, int size);
}
