package com.wyhcode.controller;

import com.wyhcode.config.MinioConfig;
import com.wyhcode.util.MinioUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author weiyuhui
 * @date 2023/8/9 10:20
 * @description
 */

@RestController
@RequestMapping("/minio")
@CrossOrigin
@Api(tags = "minio")
public class MinioController {
    @Autowired
    MinioUtils minioUtils;

    // 上传
    @PostMapping("/upload")
    @ApiOperation("上传")
    public Object upload(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        return this.minioUtils.putObject(multipartFile);
    }

    // 下载文件
    @GetMapping("/download")
    @ApiOperation("下载")
    public void download(@RequestParam("fileName")String fileName, HttpServletResponse response) {
        this.minioUtils.download(fileName,response);
    }

    // 列出所有存储桶名称
    @PostMapping("/list")
    @ApiOperation("存储桶列表")
    public List<String> list() throws Exception {
        return this.minioUtils.listBucketNames();
    }

    // 创建存储桶
    @ApiOperation("创建存储桶")
    @PostMapping("/createBucket")
    public boolean createBucket(String bucketName) throws Exception {
        return this.minioUtils.makeBucket(bucketName);
    }

    // 删除存储桶
    @ApiOperation("删除存储桶")
    @PostMapping("/deleteBucket")
    public boolean deleteBucket(String bucketName) throws Exception {
        return this.minioUtils.removeBucket(bucketName);
    }

    // 列出存储桶中的所有对象名称
    @ApiOperation("列出存储桶中的所有对象名称")
    @PostMapping("/listObjectNames")
    public List<String> listObjectNames(String bucketName) throws Exception {
        return this.minioUtils.listObjectNames(bucketName);
    }

    // 删除一个对象
    @ApiOperation("删除一个对象")
    @PostMapping("/removeObject")
    public boolean removeObject(String bucketName, String objectName) throws Exception {
        return this.minioUtils.removeObject(bucketName, objectName);
    }

    // 文件访问路径
    @ApiOperation("文件访问路径")
    @PostMapping("/getObjectUrl")
    public String getObjectUrl(String bucketName, String objectName) throws Exception {
        return this.minioUtils.getObjectUrl(bucketName, objectName);
    }
}
