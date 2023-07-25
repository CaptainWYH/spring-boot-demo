package com.wyhcode.service.student.impl;

import com.wyhcode.bean.es.Student;
import com.wyhcode.respsitory.StudentMapper;
import com.wyhcode.service.student.StudentService;
import org.springframework.stereotype.Service;

/**
 * @author weiyuhui
 * @date 2023/7/25 17:18
 * @description
 */

@Service
public class StudentServiceImpl implements StudentService {

    private StudentMapper studentMapper;

    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    public void addStudent(Student student){
        studentMapper.save(student);
    }
}
