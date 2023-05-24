package com.sell.common.utils;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 统一文件上传工具类
 * @author linyuc
 * @Description TODO
 * @date 2022/3/9 17:06
 */
@Component
@Slf4j
public class FileUploadUtil {

    @Resource
    private QiniuFileUtils qiniuFileUtils;

    @Value("${uploadFileModel}")
    public String model;

    /**
     * 本地文件上传路径
     */
    @Value("${myFile.path}")
    public String path;
    @Value("${myFile.url}")
    public String url;

    /**
     * 统一上传文件入口
     * @param file
     * @return
     * @throws IOException
     */
    public String uploadFile(MultipartFile file) throws IOException {
        if(StrUtil.equals(model,"local")) {
            return uploadFileLocal(file);
        } else if(StrUtil.equals(model,"cloud")){
            return qiniuFileUtils.uploadFile(file.getInputStream());
        }
        return "";
    }


    /**
     * 上传文件到指定本地磁盘
     * @param file
     * @return
     * @throws IOException
     */
    public String uploadFileLocal(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        //获取文件名后缀
        assert fileName != null;
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName = UUID.randomUUID()+"."+fileExtensionName;
        File fileDir = new File(path);
        if(!fileDir.exists()){
            //给权限
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);
        //保存
        file.transferTo(targetFile);
        return url+uploadFileName;
    }
}
