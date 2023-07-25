package com.wyhcode.respsitory;

import com.wyhcode.bean.es.Book;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author weiyuhui
 * @date 2023/7/25 15:17
 * @description
 */

@Repository
public interface ESBookRepository extends ElasticsearchRepository<Book,String> {

    List<Book> findByTitleOrAuthor(String title,String author);

    @Highlight(fields = {
            @HighlightField(name = "title"),
            @HighlightField(name = "author")
    })
    @Query("{\"match\":{\"title\":\"?0\"}}")
    SearchHits<Book> find(String keyword);
}
