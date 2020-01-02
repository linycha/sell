package com.sell.modules.store.service.impl;

import com.google.common.collect.Lists;
import com.sell.common.utils.FTPUtil;
import com.sell.modules.store.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author linyuc
 * @date 2019/12/20 11:50
 */
@Service
public class FileServiceImpl implements FileService {
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    @Override
    public String upload(MultipartFile file, String path){
        String fileName = file.getOriginalFilename();
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        logger.info("开始上传文件，上传文件的文件名：{}，上传的路径：{}，新文件名：{}",fileName,path,uploadFileName);
        File fileDir = new File(path);
        if(!fileDir.exists()){
            //给权限
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);

        try{
            file.transferTo(targetFile);
            //todo 将文件上传到我们的FTP服务器上
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //已经上传到ftp服务器
            targetFile.delete();
            //删除upload下面的文件，是tomcat服务器的文件夹，随着时间长，这个文件会越来越大
        }catch (IOException e){
            logger.error("上传文件异常",e);
            return null;
        }
        return targetFile.getName();
    }
}
