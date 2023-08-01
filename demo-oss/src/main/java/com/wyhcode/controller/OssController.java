package com.wyhcode.controller;

import com.wyhcode.util.OssManagerUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author weiyuhui
 * @date 2023/7/31 17:07
 * @description
 */

@Slf4j
@RestController
@RequestMapping("/oss")
@Api(tags = "对象存储管理")
public class OssController {

    @Resource
    private OssManagerUtil ossManagerUtil;

    @PostMapping("/testALoss")
    @ApiOperation("测试阿里Oss文件上传")
    public Map testALoss(MultipartFile multipartFile){
        HashMap<Object, Object> map = new HashMap<>();
        try {
            File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            FileOutputStream os = new FileOutputStream(file);
            os.write(multipartFile.getBytes());
            os.close();
            multipartFile.transferTo(file);
            log.info("文件名{}",multipartFile.getOriginalFilename());
            ossManagerUtil.upload(file,"zgx/img", multipartFile.getOriginalFilename());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}
