package com.wyhcode.mapper;

import com.wyhcode.pojo.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper {

    List<News> getNewsPage();
}
