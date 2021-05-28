package com.sell.modules.sys.controller;

import com.google.common.collect.Lists;
import com.sell.common.Const;
import com.sell.common.Res;
import com.sell.common.utils.FTPUtil;
import com.sell.common.utils.PropertiesUtil;
import com.sell.modules.store.service.FileService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;

/**
 * @author linyuc
 * @date 2020/8/12 14:40
 * 模拟云盘
 */
@RestController
@RequestMapping("file")
@Api(tags = "文件上传相关接口")
public class FileController {
    @Autowired
    private FTPUtil ftpUtil;
    @Autowired
    private FileService fileService;
    @PostMapping("upload")
    public Res<String> upload(MultipartFile file, @RequestParam(defaultValue = "") String check)throws Exception{
        if(file == null){
            return Res.errorMsg("上传文件不能为空");
        }
/*        if(!check.equals(Const.UPLOAD_CHECK)){
            return Res.errorMsg("校验码错误");
        }*/
        Long start = System.currentTimeMillis();
        boolean b = ftpUtil.uploadDailyFile(file.getOriginalFilename(),file.getInputStream(),Const.FTP_PATH_DAILY);
        Long end = System.currentTimeMillis();
        System.out.println(end - start);
        if(b){
            return Res.successMsg("上传文件成功");
        }
        return Res.errorMsg("上传文件失败");
    }
}
