package com.sell.common.utils;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;

/**
 * 七牛云文件上传工具
 */
@Slf4j
@Component
public class QiniuFileUtils implements InitializingBean {

    @Value("${qiniu.bucket}")
    private  String bucket;
    @Value("${qiniu.prefix}")
    private  String prefix;
    @Value("${qiniu.visitUrl}")
    private  String visitUrl;

    @Autowired
    private UploadManager uploadManager;
    @Autowired
    private BucketManager bucketManager;
    @Autowired
    private Auth auth;
    // 定义七牛云上传的相关策略
    private StringMap putPolicy;

    private static String directory = "sell/";

    /** * 以文件的形式上传 * * @param file * @return * @throws QiniuException */
    public String uploadFile(File file) throws QiniuException {
        Response response = this.uploadManager.put(file, null, getUploadToken());
        int retry = 0;
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(file, null, getUploadToken());
            retry++;
        }
        //解析结果
        DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
        String return_path = prefix + "/" + putRet.key;
        log.info("文件名称={}", return_path);
        return return_path;
    }

    /** * 以流的形式上传 * * @param inputStream * @return * @throws QiniuException */
    public String uploadFile(InputStream inputStream) throws QiniuException {
        Response response = this.uploadManager.put(inputStream, null, getUploadToken(), null, null);
        int retry = 0;
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(inputStream, null, getUploadToken(), null, null);
            retry++;
        }
        //解析结果
        DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
        String returnPath = visitUrl + "/" + putRet.key;
        log.info("文件名称={}", returnPath);
        return returnPath;
    }

    /** * 删除七牛云上的相关文件 * * @param key * @return * @throws QiniuException */
    public Response delete(String key) throws QiniuException {
        Response response = bucketManager.delete(bucket, key);
        int retry = 0;
        while (response.needRetry() && retry++ < 3) {
            response = bucketManager.delete(bucket, key);
        }
        return response;
    }

    @Override
    public void afterPropertiesSet() {
        this.putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"width\":$(imageInfo.width), \"height\":${imageInfo.height}}");
        // 自定义文件名字
        putPolicy.put("saveKey", directory + IdUtil.nanoId());
    }

    /** * 获取上传凭证 * * @return */
    private String getUploadToken() {
        return this.auth.uploadToken(bucket, null, 3600, putPolicy);
    }
}
