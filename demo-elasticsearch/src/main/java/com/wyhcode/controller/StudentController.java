package com.wyhcode.controller;

import com.wyhcode.bean.AjaxResult;
import com.wyhcode.bean.es.Student;
import com.wyhcode.service.student.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author weiyuhui
 * @date 2023/7/25 17:40
 * @description
 */

@Slf4j
@RestController
@Api("es - student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @PostMapping("/student")
    @ApiOperation("新增学生数据")
    public AjaxResult addStudent(@RequestBody Student student){
        studentService.addStudent(student);
        return AjaxResult.success();
    }

    @GetMapping("/high-light-search")
    @ApiOperation("高亮搜索")
    public AjaxResult highLightSearch(@RequestParam String keyword){

        return AjaxResult.success();
    }

}
