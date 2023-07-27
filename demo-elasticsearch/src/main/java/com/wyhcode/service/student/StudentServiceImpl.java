package com.wyhcode.service.student;

import com.wyhcode.bean.es.Student;
import com.wyhcode.respsitory.StudentMapper;
import com.wyhcode.service.student.StudentService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

/**
 * @author weiyuhui
 * @date 2023/7/25 17:18
 * @description
 */

@Service
public class StudentServiceImpl implements StudentService {

    private StudentMapper studentMapper;

    private RestHighLevelClient restHighLevelClient;

    public StudentServiceImpl(StudentMapper studentMapper,
                              RestHighLevelClient restHighLevelClient) {
        this.studentMapper = studentMapper;
        this.restHighLevelClient = restHighLevelClient;
    }

    public void addStudent(Student student){
        studentMapper.save(student);
    }

    @Override
    public SearchHits<Student> selectByName(String key) {
        // 创建搜索对象
        SearchRequest searchRequest = new SearchRequest("student");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("sName",key));
        String preTag = "<font color='red' font-weight='bold'>";
        String postTag = "</font>";
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("sName");
        highlightBuilder.preTags(preTag);
        highlightBuilder.postTags(postTag);
        searchRequest.source(searchSourceBuilder);
        return null;
    }
}
