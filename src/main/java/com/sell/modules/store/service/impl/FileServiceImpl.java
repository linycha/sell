package com.sell.modules.store.service.impl;

import com.sell.modules.store.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author linyuc
 * @date 2019/12/20 11:50
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    /**
     *
     * @param file 要上传的文件
     * @param path 路径
     * @return 返回上传完的文件名
    @Override
    public String upload(MultipartFile file, String path,String ftpPath){
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName = UUID.randomUUID()+"."+fileExtensionName;
        log.info("开始上传文件，上传文件的文件名：{}，上传的路径：{}，新文件名：{}",fileName,path,uploadFileName);
        //该文件夹不存在就先创建
        File fileDir = new File(path);
        if(!fileDir.exists()){
            //给权限
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);
        boolean b;
        try{
            //先上传到tomcat服务器下的target下,再把target目录下的文件上传到ftp
            //file.transferTo(targetFile);
            //todo 将文件上传到我们的FTP服务器上
            b = FTPUtil.uploadFile(ftpPath,Lists.newArrayList(targetFile));
            //已经上传到ftp服务器
            targetFile.delete();
            //删除upload下面的文件，是tomcat服务器的文件夹，随着时间长，这个文件会越来越大
        }catch (IOException e){
            log.error("上传文件到target异常",e);
            return null;
        }
        if(!b){
            return null;
        }
        return targetFile.getName();
    }*/
}
