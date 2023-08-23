package com.wyhcode.service.student;

import com.wyhcode.bean.es.Student;
import org.springframework.data.elasticsearch.core.SearchHits;

/**
 * @author weiyuhui
 * @date 2023/7/25 17:18
 * @description
 */

public interface StudentService {

    /**
     * 新增学生数据
     * @param student
     */
    public void addStudent(Student student);

    SearchHits<Student> selectByName(String key);
}
