package com.sell.common.utils;


import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import sun.net.ftp.FtpClient;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * FTP工具类
 * @author linyuc
 * @date 2019/12/20 13:45
 */
@Configuration
public class FTPUtil {

    private static final Logger logger = LoggerFactory.getLogger(FTPUtil.class);
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

    public static boolean uploadFile(List<File> fileList, String ftpPath) throws IOException {
        logger.info("开始连接ftp服务器");
        FTPUtil ftpUtil = new FTPUtil(ftpIp,21,ftpUser,ftpPass);
        boolean result = ftpUtil.uploadFile(ftpPath,fileList);
        return result;

    }
    private boolean uploadFile(String remotePath,List<File> fileList) throws IOException {
        boolean isUpload = false;
        FileInputStream fis = null;
        //连接FTP服务器
        if(open()){
            try{
                logger.info(remotePath);
                //是否需要切换文件夹，remote为空就不需要切换
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                //二进制
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                //开启被动模式
                ftpClient.enterLocalPassiveMode();
                for(File file : fileList){
                    fis = new FileInputStream(file);
                    ftpClient.storeFile(file.getName(),fis);
                }
                isUpload = true;
                logger.info("ftp服务器上传文件成功");
            }catch (IOException e){
                logger.error("ftp服务器上传文件异常",e);
                isUpload = false;
            }finally {
                fis.close();
                ftpClient.disconnect();
            }
        }
        return isUpload;
    }
    //上传日常文件，返回是否成功
    public boolean uploadDailyFile(String fileName, InputStream is, String path)throws IOException{
        boolean isUpload = false;
        //连接FTP服务器
        if(open()){
            try{
                logger.info("ftp服务器连接成功，开始上传："+fileName);
                //是否需要切换文件夹，remote为空就不需要切换
                ftpClient.changeWorkingDirectory(path);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                //二进制
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                //开启被动模式
                ftpClient.enterLocalPassiveMode();
                //上传文件
                ftpClient.storeFile(fileName,is);
                isUpload = true;
                logger.info("ftp服务器上传文件成功");
            }catch (IOException e){
                logger.error("ftp服务器上传文件异常",e);
                isUpload = false;
            }finally {
                is.close();
                ftpClient.disconnect();
            }
        }
        return isUpload;
    }
    private boolean open(){
        String ftpIp = PropertiesUtil.getProperty("ftp.serverIp");
        String ftpUser = PropertiesUtil.getProperty("ftp.user");
        String ftpPass = PropertiesUtil.getProperty("ftp.pass");
        ftpClient = new FTPClient();
        boolean isSuccess;
        try{
            ftpClient.connect(ftpIp);
            isSuccess = ftpClient.login(ftpUser,ftpPass);
        }catch (IOException e){
            logger.error("连接ftp服务器异常",e);
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
                logger.error("ftp获取"+path+"路径下文件失败");
                return null;
            }
        }else{
            logger.error("连接ftp服务器失败");
            return null;
        }
    }

    private String ip;
    private int port;
    private String user;
    private String pwd;
    private FTPClient ftpClient;

    public static String getFtpIp() {
        return ftpIp;
    }

    public static void setFtpIp(String ftpIp) {
        FTPUtil.ftpIp = ftpIp;
    }

    public static String getFtpUser() {
        return ftpUser;
    }

    public static void setFtpUser(String ftpUser) {
        FTPUtil.ftpUser = ftpUser;
    }

    public static String getFtpPass() {
        return ftpPass;
    }

    public static void setFtpPass(String ftpPass) {
        FTPUtil.ftpPass = ftpPass;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }
}
