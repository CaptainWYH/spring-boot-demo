package com.wyhcode.controller;

import com.wyhcode.bean.es.Book;
import com.wyhcode.service.BookService;
import io.swagger.annotations.Api;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weiyuhui
 * @date 2023/7/25 15:45
 * @description
 */

@RestController
@Api("es - book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public Map<String,String> addBook(@RequestBody Book book){
        bookService.addBook(book);
        HashMap<String, String> map = new HashMap<>();
        map.put("msg","ok");
        return map;
    }

    @GetMapping("/book/search")
    public SearchHits<Book> search(String key){
        return bookService.searchBookHighLight(key);
    }

}
