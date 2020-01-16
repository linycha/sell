package com.sell.modules.store.controller;

import com.google.common.collect.Maps;
import com.sell.common.Const;
import com.sell.common.ResponseCode;
import com.sell.common.Res;
import com.sell.common.utils.PropertiesUtil;
import com.sell.modules.store.entity.Product;
import com.sell.modules.sys.entity.User;
import com.sell.modules.store.service.FileService;
import com.sell.modules.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author linyuc
 * @date 2019/12/20 9:37
 */
@RestController
@RequestMapping("/product")
public class ProductShopController {
    @Autowired
    private ProductService productService;
    @Autowired
    private FileService fileService;

    @RequestMapping("save")
    public Res saveProduct(HttpSession session, Product product){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            Res.errorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),"店家未登录");
        }
        return productService.saveOrUpdateProduct(product);
    }
    @RequestMapping("status")
    public Res setStatus(HttpSession session, String productId, Integer status){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            Res.errorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),"店家未登录");
        }
        return productService.setStatus(productId,status);
    }
    @RequestMapping("detail")
    public Res getDetail(HttpSession session, String productId, Integer status){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            Res.errorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),"店家未登录");
        }
        return productService.setStatus(productId,status);
    }
    @RequestMapping("list")
    public Res getList(HttpSession session, @RequestParam(defaultValue = "1")Integer pageNum,
                                  @RequestParam(defaultValue = "1")Integer pageSize){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            Res.errorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),"店家未登录");
        }
        return productService.getProductList(pageNum,pageSize);
    }
    @RequestMapping("search")
    public Res searchProduct(HttpSession session,String productName,Integer productId, @RequestParam(defaultValue = "1")Integer pageNum,
                                  @RequestParam(defaultValue = "1")Integer pageSize){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            Res.errorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),"店家未登录");
        }
        return productService.searchProduct(productName,productId,pageNum,pageSize);
    }
    @RequestMapping("upload")
    public Res upload(@RequestParam(defaultValue = "upload_file",required=false) MultipartFile file, HttpServletRequest request){
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = fileService.upload(file,path);
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;

        Map fileMap = Maps.newHashMap();
        fileMap.put("uri",targetFileName);
        fileMap.put("url",url);
        return Res.success(fileMap);
    }




}
