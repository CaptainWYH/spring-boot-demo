package com.wyhcode.util;

import cn.hutool.core.util.ObjectUtil;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import com.wyhcode.config.OssConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author weiyuhui
 * @date 2023/7/31 17:00
 * @description
 */

@Component
@Slf4j
public class OssManagerUtil {
    private OssConfig ossConfig;

    private static String FILE_URL;

    public OssManagerUtil(OssConfig ossConfig) {
        this.ossConfig = ossConfig;
    }

    public String upload(File file,String upPath,String fileName){
        // 默认值：true
        boolean isImage = true;
        // 判断所要上传的图片是否是图片，图片可以预览，其他文件不提供通过URL浏览
        try{
            Image image = ImageIO.read(file);
            isImage = image != null;
        }catch (IOException e){
            e.printStackTrace();
        }
        log.debug("----OSS文件上传开始----" + fileName);
        // 判断文件
        if (file == null){
            return null;
        }
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(ossConfig.getEndpoint(), ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
        try {
            // 判断容器是否存在,不存在就创建
            if (!ossClient.doesBucketExist(ossConfig.getBucketName())) {
                ossClient.createBucket(ossConfig.getBucketName());
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(ossConfig.getBucketName());
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            // 设置文件路径和名称
            // String fileUrl = fileHost + "/" + (dateString + "/" + UUID.randomUUID().toString().replace("-", "") + "-" + file.getName());
            String fileUrl = upPath + "/" + fileName;
            // 上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(ossConfig.getBucketName(), fileUrl, file));
            // 设置权限(公开读)
            ossClient.setBucketAcl(ossConfig.getBucketName(), CannedAccessControlList.PublicRead);
            if (result != null) {
                //上传成功后拼接地址
                if (isImage) {//如果是图片，则图片的URL为：....
                    FILE_URL = "https://" + ossConfig.getBucketName() + "." + ossConfig.getEndpoint() + "/" + fileUrl;
                } else {
                    FILE_URL = fileUrl;
                    log.debug("非图片,不可预览。文件路径为：" + fileUrl);
                }
                log.debug("------OSS文件上传成功------" + fileUrl);
            }
        }catch (OSSException ossException){
            log.debug(ossException.getMessage());
        }catch (ClientException clientException){
            log.debug(clientException.getMessage());
        }finally {
            if (ObjectUtil.isNotNull(ossClient)){
                ossClient.shutdown();
            }
        }
        return FILE_URL;
    }

    /**
     * 创建文件夹
     *
     * @param folder
     * @return
     */
    public String createFolder(String folder) {
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(ossConfig.getEndpoint(), ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
        // 文件夹名
        final String keySuffixWithSlash = folder;
        // 判断文件夹是否存在，不存在则创建
        if (!ossClient.doesObjectExist(ossConfig.getBucketName(), keySuffixWithSlash)) {
            // 创建文件夹
            ossClient.putObject(ossConfig.getBucketName(), keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
            log.debug("创建文件夹成功");
            // 得到文件夹名
            OSSObject object = ossClient.getObject(ossConfig.getBucketName(), keySuffixWithSlash);
            String fileDir = object.getKey();
            ossClient.shutdown();
            return fileDir;
        }
        return keySuffixWithSlash;
    }
}
