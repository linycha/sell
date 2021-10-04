package com.sell.common.utils;


import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

/**
 * FTP工具类
 * @author linyuc
 * @date 2019/12/20 13:45
 */
@Configuration
@Slf4j
@Data
public class FTPUtil {

    private static String ftpIp = PropertiesUtil.getProperty("ftp.serverIp");
    private static String ftpUser = PropertiesUtil.getProperty("ftp.user");
    private static String ftpPass = PropertiesUtil.getProperty("ftp.pass");

    public FTPUtil(String ip,int port, String user,String pwd){
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
    }

    public FTPUtil() {
    }

/*    public static boolean uploadFile(List<File> fileList, String ftpPath) throws IOException {
        logger.info("开始连接ftp服务器");
        FTPUtil ftpUtil = new FTPUtil(ftpIp,21,ftpUser,ftpPass);
        return ftpUtil.uploadFile(ftpPath,fileList);
    }*/

    /**
     * ftp上传文件,返回上传到服务器上的文件名
     * @param ftpPath ftp下文件夹
     * @return
     * @throws IOException
     */
    public String uploadFile(MultipartFile file, String path, String ftpPath) throws Exception {
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName = UUID.randomUUID()+"."+fileExtensionName;
        log.info("开始上传文件，上传文件的文件名：{}，上传的源路径：{}，新文件名：{}",fileName,path,uploadFileName);
        //该文件夹不存在就先创建
        File fileDir = new File(path);
        if(!fileDir.exists()){
            //给权限
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);
        List<File> fileList = Lists.newArrayList(targetFile);
        FileInputStream fis = null;
        //连接FTP服务器
        if(open()){
            try{
                file.transferTo(targetFile);
                //是否需要切换文件夹，remote为空就不需要切换
                ftpClient.changeWorkingDirectory(ftpPath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                //二进制,开启被动模式
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                for(File f : fileList){
                    fis = new FileInputStream(f);
                    ftpClient.storeFile(f.getName(),fis);
                }
                targetFile.delete();
                log.info("ftp服务器上传文件成功");
            }catch (IOException e){
                log.error("ftp服务器上传文件失败",e);
                throw new Exception("ftp服务器上传文件异常");
            }finally {
                fis.close();
                ftpClient.disconnect();
            }
        }
        return uploadFileName;
    }
    //上传日常文件，返回是否成功
    public boolean uploadDailyFile(String fileName, InputStream is, String ftpPath)throws IOException{
        boolean isUpload = false;
        //连接FTP服务器
        if(open()){
            try{
                log.info("ftp服务器连接成功，开始上传："+fileName);
                //是否需要切换文件夹，remote为空就不需要切换
                ftpClient.changeWorkingDirectory(ftpPath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                //二进制
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                //开启被动模式
                ftpClient.enterLocalPassiveMode();
                //上传文件
                ftpClient.storeFile(fileName,is);
                isUpload = true;
                log.info("ftp服务器上传文件成功");
            }catch (IOException e){
                log.error("ftp服务器上传文件异常",e);
                isUpload = false;
            }finally {
                is.close();
                ftpClient.disconnect();
            }
        }
        return isUpload;
    }
    private boolean open(){
        log.info("开始连接ftp服务器");
        String ftpIp = PropertiesUtil.getProperty("ftp.serverIp");
        String ftpUser = PropertiesUtil.getProperty("ftp.user");
        String ftpPass = PropertiesUtil.getProperty("ftp.pass");
        ftpClient = new FTPClient();
        boolean isSuccess;
        try{
            ftpClient.connect(ftpIp);
            isSuccess = ftpClient.login(ftpUser,ftpPass);
        }catch (IOException e){
            log.error("连接ftp服务器异常",e);
            isSuccess = false;
        }
        return isSuccess;
    }
    public List<FTPFile> getFileList(String path){
        //FTPUtil ftpUtil = new FTPUtil(ftpIp,21,ftpUser,ftpPass);
        if(open()){
            try{
                //String[] list = (ftpClient.listNames(path));
                FTPFile[] fileList = ftpClient.listFiles(path);
                for(FTPFile file : fileList){
                    long size = file.getSize()/1024;
                    String date = DateTimeUtil.dateToStr(file.getTimestamp().getTime());
                    System.out.println("名字"+file.getName()+"大小："+size+"时间"+date+"类型："+file.getType());
                }
                return Arrays.asList(fileList);
            }catch (IOException e){
                log.error("ftp获取"+path+"路径下文件失败");
                return null;
            }
        }else{
            log.error("连接ftp服务器失败");
            return null;
        }
    }

    private String ip;
    private int port;
    private String user;
    private String pwd;
    private FTPClient ftpClient;
}
