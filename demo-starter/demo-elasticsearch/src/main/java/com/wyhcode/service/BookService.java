package com.wyhcode.service;

import com.wyhcode.bean.es.Book;
import com.wyhcode.respsitory.ESBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * @author weiyuhui
 * @date 2023/7/25 15:32
 * @description
 */

@Service
@Slf4j
public class BookService {


    private final ESBookRepository esBookRepository;


    public BookService(ESBookRepository esBookRepository
                       ){
        this.esBookRepository = esBookRepository;
    }

    public void addBook(Book book){

        try {
            esBookRepository.save(book);
        } catch (Exception e) {
            log.error("保存ES错误！{}",e.getMessage());
        }
    }

    public List<Book> searchBook(String keyword){
        return esBookRepository.findByTitleOrAuthor(keyword,keyword);
    }

    public SearchHits<Book> searchBookHighLight(String keyword){
        return esBookRepository.find(keyword);
    }
}
