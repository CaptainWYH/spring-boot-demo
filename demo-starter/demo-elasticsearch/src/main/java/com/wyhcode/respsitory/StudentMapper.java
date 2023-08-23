package com.wyhcode.respsitory;

import com.wyhcode.bean.es.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author weiyuhui
 * @date 2023/7/25 17:16
 * @description
 */

@Repository
public interface StudentMapper extends ElasticsearchRepository<Student,String> {
}
